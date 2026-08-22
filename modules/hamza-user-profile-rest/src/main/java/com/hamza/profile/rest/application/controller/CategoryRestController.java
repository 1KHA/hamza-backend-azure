package com.hamza.profile.rest.application.controller;

import com.liferay.asset.category.property.model.AssetCategoryProperty;
import com.liferay.asset.category.property.service.AssetCategoryPropertyLocalService;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.hamza.profile.rest.util.HeaderLanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.query.*;
import com.liferay.portal.search.searcher.*;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Pranavsinh Parmar
 */
@Component(
    property = {
            JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/hamza-test-category",
            JaxrsWhiteboardConstants.JAX_RS_NAME + "=hamza-test-category.Rest"
    },
    service = Application.class
)
public class CategoryRestController extends Application {

    private static final Log LOG = LogFactoryUtil.getLog(CategoryRestController.class);
    
    private static final String CACHE_NAME = "com.hamza.profile.rest.category.cache";
    private PortalCache<String, JSONArray> _cache;

    @Reference
    private GroupLocalService groupLocalService;

    @Reference
    private AssetVocabularyLocalService assetVocabularyLocalService;

    @Reference
    private AssetCategoryLocalService assetCategoryLocalService;

    @Reference
    private AssetCategoryPropertyLocalService assetCategoryPropertyLocalService;

    @Reference
    protected Queries queries;

    @Reference
    protected Searcher searcher;

    @Reference
    protected SearchRequestBuilderFactory searchRequestBuilderFactory;

    @Reference
    private SingleVMPool _singleVMPool;


    @Activate
    @SuppressWarnings("unchecked")
    public void activate() {
        _cache = (PortalCache<String, JSONArray>) _singleVMPool.getPortalCache(CACHE_NAME);
    }

    @Deactivate
    public void deactivate() {
        _singleVMPool.removePortalCache(CACHE_NAME);
    }

    public Set<Object> getSingletons() {
        return Collections.<Object>singleton(this);
    }


    @GET
    @Path("/vocabulary/child/categories/{vocabularyName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getListOfChildCategories(@PathParam("vocabularyName")String vocabularyName, @Context HttpServletRequest request) {


        Locale locale = LocaleUtil.fromLanguageId("ar_SA");
        String languageId = HeaderLanguageUtil.getLanguageIdFromAcceptLanguage(request);

        LOG.error("languageId :" + languageId);


        if (languageId != null && languageId.contains("_")) {
            locale = LocaleUtil.fromLanguageId(languageId);
        }


        // Create unique cache key based on vocabulary name and language ID
        String cacheKey = "vocabulary_child_categories_" + vocabularyName + "_" + languageId;
        
        // Try to get from cache first (if cache is initialized)
        JSONArray array = null;
        if (_cache != null) {
            array = _cache.get(cacheKey);
            if (array != null) {
                LOG.info("Cache hit for vocabulary: " + vocabularyName + ", languageId: " + languageId);
                return array.toJSONString();
            }
        }
        
        LOG.info("Cache miss for vocabulary: " + vocabularyName + ", languageId: " + languageId + ", building new data");
        array = JSONFactoryUtil.createJSONArray();

        Group globalGroup = groupLocalService.fetchFriendlyURLGroup(PortalUtil.getDefaultCompanyId(), "/global");

        if (globalGroup != null) {
            AssetVocabulary assetVocabulary = assetVocabularyLocalService.
                    fetchGroupVocabulary(globalGroup.getGroupId(), vocabularyName);

            ClassLoader classLoader = AssetCategory.class.getClassLoader();
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
                    AssetCategory.class,
                    classLoader
            );

            long zero = 0L;

            dynamicQuery.add(PropertyFactoryUtil.forName("vocabularyId").eq(assetVocabulary.getVocabularyId()));
            dynamicQuery.add(PropertyFactoryUtil.forName("parentCategoryId").eq(zero));

            List<AssetCategory> categoriesList = assetCategoryLocalService.dynamicQuery(dynamicQuery);

            for (AssetCategory assetCategory : categoriesList) {
                JSONObject object = JSONFactoryUtil.createJSONObject();
                object.put("label", assetCategory.getTitle(locale));

                AssetCategoryProperty assetCategoryProperty = assetCategoryPropertyLocalService.
                        fetchCategoryProperty(assetCategory.getCategoryId(), "code");

                if (assetCategoryProperty != null) {
                    object.put("key", assetCategoryProperty.getValue());
                    object.put("categoryId", assetCategory.getCategoryId());
                    object.put("vocabularyId", assetCategory.getVocabularyId());
                    array.put(object);
                } else {
                    LOG.error("AssetCategory property is null for category Id :" + assetCategory.getCategoryId());
                }
            }
        } else {
            LOG.error("Global group not found");
        }
        
        // Cache the result for future requests (if cache is initialized)
        if (_cache != null) {
            _cache.put(cacheKey, array);
            LOG.info("Cached data for vocabulary: " + vocabularyName + ", languageId: " + languageId);
        }
        
        return array.toJSONString();
    }

    @GET
    @Path("/category/child/categories/{vocabularyId}/{parentCategoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getListOfChildCategoriesByParentId(@PathParam("vocabularyId")long vocabularyId, @PathParam("parentCategoryId")long parentCategoryId, @Context HttpServletRequest request) {


        Locale locale = LocaleUtil.fromLanguageId("ar_SA");
        String languageId = HeaderLanguageUtil.getLanguageIdFromAcceptLanguage(request);

        LOG.error("languageId :" + languageId);

        if (languageId != null && languageId.contains("_")) {
            locale = LocaleUtil.fromLanguageId(languageId);
        }

        // Create unique cache key based on vocabulary and parent category IDs and language ID
        String cacheKey = "category_child_categories_" + vocabularyId + "_" + parentCategoryId + "_" + languageId;
        
        // Try to get from cache first (if cache is initialized)
        JSONArray array = null;
        if (_cache != null) {
            array = _cache.get(cacheKey);
            if (array != null) {
                LOG.info("Cache hit for vocabularyId: " + vocabularyId + ", parentCategoryId: " + parentCategoryId + ", languageId: " + languageId);
                return array.toJSONString();
            }
        }
        
        LOG.info("Cache miss for vocabularyId: " + vocabularyId + ", parentCategoryId: " + parentCategoryId + ", languageId: " + languageId + ", building new data");

        ClassLoader classLoader = AssetCategory.class.getClassLoader();
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
                AssetCategory.class,
                classLoader
        );

        dynamicQuery.add(PropertyFactoryUtil.forName("vocabularyId").eq(vocabularyId));
        dynamicQuery.add(PropertyFactoryUtil.forName("parentCategoryId").eq(parentCategoryId));

        List<AssetCategory> categoriesList = assetCategoryLocalService.dynamicQuery(dynamicQuery);

        array = JSONFactoryUtil.createJSONArray();

        if (categoriesList != null) {

            for (AssetCategory assetCategory : categoriesList) {
                JSONObject object = JSONFactoryUtil.createJSONObject();
                object.put("label", assetCategory.getTitle(locale));

                AssetCategoryProperty assetCategoryProperty = assetCategoryPropertyLocalService.
                        fetchCategoryProperty(assetCategory.getCategoryId(), "code");

                if (assetCategoryProperty != null) {
                    object.put("key", assetCategoryProperty.getValue());
                    object.put("categoryId", assetCategory.getCategoryId());
                    object.put("vocabularyId", assetCategory.getVocabularyId());
                    array.put(object);
                } else {
                    LOG.error("AssetCategory property is null for category Id :" + assetCategory.getCategoryId());
                }
            }
        }
        
        // Cache the result for future requests (if cache is initialized)
        if (_cache != null) {
            _cache.put(cacheKey, array);
            LOG.info("Cached data for vocabularyId: " + vocabularyId + ", parentCategoryId: " + parentCategoryId + ", languageId: " + languageId);
        }

        return array.toJSONString();
    }

    private boolean matchesField(String fieldName, String sid, String suffix, String languageId) {
        if (fieldName == null) return false;
        return fieldName.endsWith("__" + sid + "__" + suffix + "_" + languageId);
    }

    private String getFirstStringFromMap(LinkedHashMap<?, ?> map, String key) {
        Object val = map.get(key);
        if (val instanceof ArrayList && !((ArrayList<?>) val).isEmpty()) {
            return String.valueOf(((ArrayList<?>) val).get(0));
        }
        return null;
    }

    @POST
    @Path("/get/news-articles/{structureKey}/{typeVocabularyName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String searchNewsArticles(@Context HttpServletRequest request, @PathParam("structureKey")String structureKey, @PathParam("typeVocabularyName")String typeVocabularyName) {

        String searchText = ParamUtil.getString(request, "searchText");
        String selectedYear = ParamUtil.getString(request, "selectedYear");
        long selectedArticleType = ParamUtil.getLong(request, "selectedArticleType");
        String locale = ParamUtil.getString(request, "locale");

        // First try to get language from Accept-Language header, with Locale.US as fallback
        String languageId = HeaderLanguageUtil.getLanguageIdFromAcceptLanguage(request, Locale.US);
        
        // If a specific locale parameter is provided, use it instead
        if (locale != null && locale.contains("-")) {
            languageId = locale.replace("-", "_");
        }

        LOG.error("structureKey :" + structureKey + ", searchText :" + searchText + ", selectedYear :" + selectedYear + ", selectedArticleType :" + selectedArticleType + ", languageId :" + languageId);

        try {
            Group globalGroup = GroupLocalServiceUtil.fetchFriendlyURLGroup(PortalUtil.getDefaultCompanyId(), "/global");
            if (globalGroup == null) throw new IllegalStateException("Global group not found");

            long classNameId = ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class);
            DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getStructure(globalGroup.getGroupId(), classNameId, structureKey);
            if (ddmStructure == null) throw new IllegalStateException("DDM Structure not found for key: " + structureKey);

            LOG.error("ddmStructure :" + ddmStructure.getStructureKey());

            // Build query
            BooleanQuery booleanQuery = queries.booleanQuery();

            if (searchText != null && !searchText.isEmpty()) {
                // Create match query for the field value
                MatchQuery fieldValueQuery = queries.match(
                        "ddmFieldArray.ddmFieldValueText_" + languageId,
                        searchText
                );

                String titleTextFieldName = "ddm__text__" + ddmStructure.getStructureId() + "__titleText_" + languageId;

                // Create term query for the field name
                TermQuery fieldNameQuery = queries.term(
                        "ddmFieldArray.ddmFieldName",
                        titleTextFieldName
                );

                // Combine field name and value in boolean query
                BooleanQuery innerBooleanQuery = queries.booleanQuery();
                innerBooleanQuery.addMustQueryClauses(fieldNameQuery, fieldValueQuery);

                // Wrap in nested query
                NestedQuery nestedQuery = queries.nested(
                        "ddmFieldArray",
                        innerBooleanQuery
                );
                booleanQuery.addMustQueryClauses(nestedQuery);
            }

            if (selectedArticleType > 0) {
                TermQuery termQuery = queries.term("assetCategoryIds", String.valueOf(selectedArticleType));
                booleanQuery.addMustQueryClauses(termQuery);
            }

            if (selectedYear != null && !selectedYear.isEmpty()) {
                // Create match query for the field value
                String yearKeyword = "*" + selectedYear + "*";
                WildcardQuery fieldValueQuery = queries.wildcard(
                        "ddmFieldArray.ddmFieldValueKeyword_" + languageId,
                        yearKeyword
                );

                String articleDateFieldName = "ddm__keyword__" + ddmStructure.getStructureId() + "__articleDate_" + languageId;;
                // Create term query for the field name
                TermQuery fieldNameQuery = queries.term(
                        "ddmFieldArray.ddmFieldName",
                        articleDateFieldName
                );

                // Combine field name and value in boolean query
                BooleanQuery innerBooleanQuery = queries.booleanQuery();
                innerBooleanQuery.addMustQueryClauses(fieldNameQuery, fieldValueQuery);

                // Wrap in nested query
                NestedQuery nestedQuery = queries.nested(
                        "ddmFieldArray",
                        innerBooleanQuery
                );
                booleanQuery.addMustQueryClauses(nestedQuery);
            }

            booleanQuery.addMustQueryClauses(
                    queries.term(Field.ENTRY_CLASS_NAME, JournalArticle.class.getName()),
                    queries.term(Field.GROUP_ID, globalGroup.getGroupId()),
                    queries.term("latest", true),
                    queries.term("ddmStructureKey", ddmStructure.getStructureKey())
            );

            // Search request
            SearchRequestBuilder searchRequestBuilder = searchRequestBuilderFactory.builder();
            searchRequestBuilder.emptySearchEnabled(true);
            searchRequestBuilder.withSearchContext(sc -> sc.setCompanyId(PortalUtil.getDefaultCompanyId()));

            SearchRequest searchRequest = searchRequestBuilder.query(booleanQuery).build();
            SearchResponse searchResponse = searcher.search(searchRequest);
            SearchHits searchHits = searchResponse.getSearchHits();

            LOG.error("searchHits :" + searchHits.getSearchHits().size());

            // Field name conventions
            String sid = String.valueOf(ddmStructure.getStructureId());

            JSONArray result = JSONFactoryUtil.createJSONArray();
            Set<String> articleYearList = new HashSet<>();
            Set<String> assetCategoryList = new HashSet<>();

            for (SearchHit hit : searchHits.getSearchHits()) {
                Document doc = hit.getDocument();
                List<Object> ddmFieldArrayJsons = doc.getValues("ddmFieldArray");
                JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
                jsonObject.put(Field.ENTRY_CLASS_PK, doc.getString(Field.ENTRY_CLASS_PK));

                List<Object> list = doc.getValues("assetCategoryIds");

                List<String> categiryStringList = list.stream()
                        .filter(Objects::nonNull)
                        .map(String::valueOf)
                        .toList();

                assetCategoryList.addAll(categiryStringList);

                for (Object ddmFieldObj : ddmFieldArrayJsons) {
                    if (!(ddmFieldObj instanceof LinkedHashMap)) continue;
                    LinkedHashMap<?, ?> ddmFieldMap = (LinkedHashMap<?, ?>) ddmFieldObj;
                    String fieldName = getFirstStringFromMap(ddmFieldMap, "ddmFieldName");

                    String fieldValue = getFirstStringFromMap(ddmFieldMap, "ddmFieldValueKeyword_" + languageId);
                    String ddmFieldValueText = getFirstStringFromMap(ddmFieldMap, "ddmFieldValueText_" + languageId);

                    // Map fields by suffix
                    if (matchesField(fieldName, sid, "titleText", languageId)) jsonObject.put("titleText", ddmFieldValueText);
                    else if (matchesField(fieldName, sid, "paragraphText1", languageId)) jsonObject.put("paragraphText1", ddmFieldValueText);
                    else if (matchesField(fieldName, sid, "paragraph2text", languageId)) jsonObject.put("paragraph2text", ddmFieldValueText);
                    else if (matchesField(fieldName, sid, "authorText", languageId)) jsonObject.put("authorText", fieldValue);
                    else if (matchesField(fieldName, sid, "locationText", languageId)) jsonObject.put("locationText", fieldValue);
                    else if (matchesField(fieldName, sid, "imageThumbnail", languageId) && fieldValue != null) {
                        JSONObject imageObj = JSONFactoryUtil.createJSONObject(fieldValue);
                        jsonObject.put("imageThumbnailUrl", imageObj.getString("url"));
                    }
                    else if (matchesField(fieldName, sid, "heroImage", languageId)) jsonObject.put("heroImage", fieldValue);
                    else if (matchesField(fieldName, sid, "articleDate", languageId)) {
                        jsonObject.put("articleDate", fieldValue);
                        LocalDate date = LocalDate.parse(fieldValue);
                        int year = date.getYear();
                        articleYearList.add(String.valueOf(year));
                    }
                }
                result.put(jsonObject);
            }

            AssetVocabulary assetVocabulary =  assetVocabularyLocalService.fetchGroupVocabulary
                    (globalGroup.getGroupId(), typeVocabularyName);

            JSONArray assetCAtegoryJsonArray = JSONFactoryUtil.createJSONArray();

            for (String categoryId : assetCategoryList) {
                AssetCategory assetCategory = assetCategoryLocalService.fetchCategory(Long.parseLong(categoryId));
                if (assetVocabulary.getVocabularyId() == assetCategory.getVocabularyId()) {

                    JSONObject object = JSONFactoryUtil.createJSONObject();
                    object.put("name", assetCategory.getTitle(languageId));
                    object.put("categoryId", assetCategory.getCategoryId());

                    assetCAtegoryJsonArray.put(object);
                }
            }

            JSONArray yearListJsonArray = JSONFactoryUtil.createJSONArray();

            for (String author : articleYearList) {
                yearListJsonArray.put(author);
            }

            JSONObject response = JSONFactoryUtil.createJSONObject();
            response.put("articleList", result);
            response.put("articleYearList", yearListJsonArray);
            response.put("articleTypesList", assetCAtegoryJsonArray);

            return response.toJSONString();
        } catch (PortalException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    @GET
    @Path("/get/news-article/{languageId}/{entryClassPK}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getArticleByClassPK(@Context HttpServletRequest request, @PathParam("languageId")String languageId, @PathParam("entryClassPK")String entryClassPK) throws JSONException {

        if (languageId != null && languageId.contains("-")) {
            languageId = languageId.replace("-", "_");
        }

        BooleanQuery booleanQuery = queries.booleanQuery();

        booleanQuery.addMustQueryClauses(
                queries.term(Field.ENTRY_CLASS_NAME, JournalArticle.class.getName()),
                queries.term(Field.ENTRY_CLASS_PK, entryClassPK)
        );

        // Search request
        SearchRequestBuilder searchRequestBuilder = searchRequestBuilderFactory.builder();
        searchRequestBuilder.emptySearchEnabled(true);
        searchRequestBuilder.withSearchContext(sc -> sc.setCompanyId(PortalUtil.getDefaultCompanyId()));

        SearchRequest searchRequest = searchRequestBuilder.query(booleanQuery).build();
        SearchResponse searchResponse = searcher.search(searchRequest);
        SearchHits searchHits = searchResponse.getSearchHits();

        LOG.error("searchHits :" + searchHits.getSearchHits().size());

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

        for (SearchHit hit : searchHits.getSearchHits()) {
            Document doc = hit.getDocument();
            List<Object> ddmFieldArrayJsons = doc.getValues("ddmFieldArray");


            String sid = doc.getString("classTypeId");

            for (Object ddmFieldObj : ddmFieldArrayJsons) {
                if (!(ddmFieldObj instanceof LinkedHashMap)) continue;
                LinkedHashMap<?, ?> ddmFieldMap = (LinkedHashMap<?, ?>) ddmFieldObj;
                String fieldName = getFirstStringFromMap(ddmFieldMap, "ddmFieldName");

                String fieldValue = getFirstStringFromMap(ddmFieldMap, "ddmFieldValueKeyword_" + languageId);
                String ddmFieldValueText = getFirstStringFromMap(ddmFieldMap, "ddmFieldValueText_" + languageId);

                // Map fields by suffix
                if (matchesField(fieldName, sid, "titleText", languageId)) jsonObject.put("titleText", ddmFieldValueText);
                else if (matchesField(fieldName, sid, "paragraphText1", languageId)) jsonObject.put("paragraphText1", ddmFieldValueText);
                else if (matchesField(fieldName, sid, "paragraph2text", languageId)) jsonObject.put("paragraph2text", ddmFieldValueText);
                else if (matchesField(fieldName, sid, "authorText", languageId)) jsonObject.put("authorText", fieldValue);
                else if (matchesField(fieldName, sid, "imageThumbnail", languageId) && fieldValue != null) {
                    JSONObject imageObj = JSONFactoryUtil.createJSONObject(fieldValue);
                    jsonObject.put("imageThumbnailUrl", imageObj.getString("url"));
                }
                else if (matchesField(fieldName, sid, "heroImage", languageId)) {
                    JSONObject imageObj = JSONFactoryUtil.createJSONObject(fieldValue);
                    jsonObject.put("heroImage", imageObj.getString("url"));
                }
                else if (matchesField(fieldName, sid, "articleDate", languageId)) {
                    jsonObject.put("articleDate", fieldValue);
                } else if (matchesField(fieldName, sid, "paragraphImage", languageId)) {
                    JSONObject imageObj = JSONFactoryUtil.createJSONObject(fieldValue);
                    jsonObject.put("paragraphImage", imageObj.getString("url"));
                }
            }
        }
        return jsonObject.toJSONString();
    }

}
