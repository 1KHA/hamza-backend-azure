package com.hamza.booking.admin.widget.model;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to map JSON responses to POJOs.
 * 
 * @author hamza
 */
public class TestResponseMapper {

	public static TestResponse mapToTestResponse(
		String jsonString, JSONFactory jsonFactory) throws JSONException {

		JSONObject jsonObject = jsonFactory.createJSONObject(jsonString);
		TestResponse response = new TestResponse();

		response.setActions(jsonObject.get("actions"));
		response.setFacets(mapToObjectList(
			jsonObject.getJSONArray("facets")));
		response.setItems(mapToTestItemList(
			jsonObject.getJSONArray("items"), jsonFactory));
		response.setLastPage(jsonObject.getInt("lastPage"));
		response.setPage(jsonObject.getInt("page"));
		response.setPageSize(jsonObject.getInt("pageSize"));
		response.setTotalCount(jsonObject.getInt("totalCount"));

		return response;
	}

	private static List<TestItem> mapToTestItemList(
		JSONArray itemsArray, JSONFactory jsonFactory) {

		List<TestItem> items = new ArrayList<>();

		if (itemsArray != null) {
			for (int i = 0; i < itemsArray.length(); i++) {
				JSONObject itemJson = itemsArray.getJSONObject(i);
				TestItem item = mapToTestItem(itemJson, jsonFactory);
				items.add(item);
			}
		}

		return items;
	}

	private static TestItem mapToTestItem(
		JSONObject itemJson, JSONFactory jsonFactory) {

		TestItem item = new TestItem();

		item.setActions(itemJson.get("actions"));
		item.setCreator(mapToCreator(itemJson.getJSONObject("creator")));
		item.setDateCreated(itemJson.getString("dateCreated"));
		item.setDateModified(itemJson.getString("dateModified"));
		item.setExternalReferenceCode(
			itemJson.getString("externalReferenceCode"));
		item.setId(itemJson.getLong("id"));
		item.setKeywords(mapToStringList(
			itemJson.getJSONArray("keywords")));
		item.setStatus(mapToStatus(itemJson.getJSONObject("status")));
		item.setTaxonomyCategoryBriefs(mapToObjectList(
			itemJson.getJSONArray("taxonomyCategoryBriefs")));
		item.setR_testCenterRelationship_c_testCenterId(
			itemJson.getLong("r_testCenterRelationship_c_testCenterId"));
		item.setR_testCenterRelationship_c_testCenterERC(
			itemJson.getString("r_testCenterRelationship_c_testCenterERC"));
		item.setTestStatus(mapToKeyValueObject(
			itemJson.getJSONObject("testStatus")));
		item.setStartTime(mapToKeyValueObject(
			itemJson.getJSONObject("startTime")));
		item.setEndTime(mapToKeyValueObject(
			itemJson.getJSONObject("endTime")));
		item.setTypeOfTheTest(mapToKeyValueObject(
			itemJson.getJSONObject("typeOfTheTest")));
		item.setTestCenterRelationshipERC(
			itemJson.getString("testCenterRelationshipERC"));
		item.setTestDate(itemJson.getString("testDate"));
		item.setCapacity(itemJson.getInt("capacity"));

		return item;
	}

	private static Creator mapToCreator(JSONObject creatorJson) {
		if (creatorJson == null) {
			return null;
		}

		Creator creator = new Creator();
		creator.setAdditionalName(creatorJson.getString("additionalName"));
		creator.setContentType(creatorJson.getString("contentType"));
		creator.setExternalReferenceCode(
			creatorJson.getString("externalReferenceCode"));
		creator.setFamilyName(creatorJson.getString("familyName"));
		creator.setGivenName(creatorJson.getString("givenName"));
		creator.setId(creatorJson.getLong("id"));
		creator.setName(creatorJson.getString("name"));

		return creator;
	}

	private static Status mapToStatus(JSONObject statusJson) {
		if (statusJson == null) {
			return null;
		}

		Status status = new Status();
		status.setCode(statusJson.getInt("code"));
		status.setLabel(statusJson.getString("label"));
		status.setLabel_i18n(statusJson.getString("label_i18n"));

		return status;
	}

	private static KeyValueObject mapToKeyValueObject(JSONObject kvJson) {
		if (kvJson == null) {
			return null;
		}

		KeyValueObject kv = new KeyValueObject();
		kv.setKey(kvJson.getString("key"));
		kv.setName(kvJson.getString("name"));

		return kv;
	}

	private static List<String> mapToStringList(JSONArray array) {
		List<String> list = new ArrayList<>();

		if (array != null) {
			for (int i = 0; i < array.length(); i++) {
				list.add(array.getString(i));
			}
		}

		return list;
	}

	private static List<Object> mapToObjectList(JSONArray array) {
		List<Object> list = new ArrayList<>();

		if (array != null) {
			for (int i = 0; i < array.length(); i++) {
				list.add(array.get(i));
			}
		}

		return list;
	}
}

