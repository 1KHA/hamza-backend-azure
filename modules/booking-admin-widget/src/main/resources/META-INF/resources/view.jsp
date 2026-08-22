<%@ include file="/init.jsp" %>

<link rel="stylesheet" href="<%= renderRequest.getContextPath() %>/lib/jquery.dataTables.min.css" />
<script src="<%= renderRequest.getContextPath() %>/lib/jquery-3.7.1.min.js"></script>
<script>
	// Enable jQuery no-conflict mode to avoid conflicts with other libraries
	// This restores the $ variable but keeps jQuery available
	jQuery.noConflict();
</script>
<script src="<%= renderRequest.getContextPath() %>/lib/jquery.dataTables.min.js"></script>

<%@ page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="com.liferay.portal.kernel.json.JSONArray" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedHashSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="javax.portlet.ResourceURL" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%
	String testsJson = (String)request.getAttribute("testsJson");
	Boolean noRecords = (Boolean)request.getAttribute("noRecords");
	String noRecordsMessage = (String)request.getAttribute("noRecordsMessage");
	if (noRecordsMessage == null || noRecordsMessage.isEmpty()) {
		noRecordsMessage = "No tests found. There are no records available.";
	}

	List<JSONObject> testItems = new ArrayList<JSONObject>();
	String testDatesJson = "[]";
	String safeTestsJson = "{}";

	// Create resource URL for fetching bookings
	ResourceURL resourceURL = renderResponse.createResourceURL();
	resourceURL.setResourceID("fetchBookings");
	String resourceURLString = resourceURL.toString();

	// Create resource URL for exporting bookings to CSV
	ResourceURL exportCsvURL = renderResponse.createResourceURL();
	exportCsvURL.setResourceID("exportBookingsCSV");
	String exportCsvURLString = exportCsvURL.toString();

	// Create resource URL for exporting data for Hamza
	ResourceURL exportHamzaURL = renderResponse.createResourceURL();
	exportHamzaURL.setResourceID("exportHamzaData");
	String exportHamzaURLString = exportHamzaURL.toString();

	// Create resource URL for uploading CSV
	ResourceURL uploadCsvURL = renderResponse.createResourceURL();
	uploadCsvURL.setResourceID("uploadCSV");
	String uploadCsvURLString = uploadCsvURL.toString();

	if (testsJson != null && !testsJson.isEmpty()) {
		JSONObject root = JSONFactoryUtil.createJSONObject(testsJson);

		JSONArray items = root.getJSONArray("items");

		Set<String> testDateSet = new LinkedHashSet<String>();

		SimpleDateFormat apiFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		apiFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = 0; i < items.length(); i++) {
			JSONObject test = items.getJSONObject(i);

			testItems.add(test);

			String testDateRaw = test.getString("testDate");

			if (testDateRaw != null && !testDateRaw.isEmpty()) {
				try {
					Date date = apiFormat.parse(testDateRaw);

					testDateSet.add(dayFormat.format(date));
				}
				catch (Exception e) {
					// ignore parse errors
				}
			}
		}

		JSONArray testDatesArray = JSONFactoryUtil.createJSONArray();

		for (String d : testDateSet) {
			testDatesArray.put(d);
		}

		testDatesJson = testDatesArray.toString();
		safeTestsJson = testsJson;
	}
%>

<c:if test="<%= !testItems.isEmpty() %>">

	<%
		String currentMvcPath = ParamUtil.getString(renderRequest, "mvcPath", "");
		boolean isBookingsActive = currentMvcPath.isEmpty() || "/view.jsp".equals(currentMvcPath);
	%>

	<!-- Navigation Tabs -->
	<ul class="nav nav-tabs" id="<portlet:namespace />bookingTabs" role="tablist">
		<li class="nav-item" role="presentation">
			<portlet:renderURL var="bookingsURL" />
			<a class="nav-link <%= isBookingsActive ? "active" : "" %>" href="<%= bookingsURL %>">
				Bookings
			</a>
		</li>
	</ul>

	<div class="tab-content" id="<portlet:namespace />bookingTabContent">
		<!-- Bookings Content -->
		<div class="mt-3">
			<h4>Test Calendar & Bookings</h4>

			<div class="calendar-bookings-wrapper">
		<div
			id="<portlet:namespace />testCalendarWrapper"
			class="test-calendar-container"
		>
		<div class="test-calendar-header">
			<button
				class="btn btn-sm btn-secondary"
				id="<portlet:namespace />prevMonth"
			>
				&larr;
			</button>
			<h5
				id="<portlet:namespace />currentMonthYear"
				class="test-calendar-title"
			></h5>
			<button
				class="btn btn-sm btn-secondary"
				id="<portlet:namespace />nextMonth"
			>
				&rarr;
			</button>
		</div>
		<table class="table table-bordered test-calendar-table">
			<thead>
				<tr>
					<th>Sun</th>
					<th>Mon</th>
					<th>Tue</th>
					<th>Wed</th>
					<th>Thu</th>
					<th>Fri</th>
					<th>Sat</th>
				</tr>
			</thead>
			<tbody id="<portlet:namespace />calendarBody"></tbody>
		</table>
	</div>

	<div class="bookings-container">
		<div class="bookings-header" style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px;">
			<h5 id="<portlet:namespace />bookingsTitle" style="margin: 0;">Select a date to view bookings</h5>
			<div style="display: flex; gap: 10px;">
				<button 
					id="<portlet:namespace />exportCsvBtn" 
					class="btn btn-primary btn-sm"
					style="display: none;"
				>
					Export CSV
				</button>
				<button 
					id="<portlet:namespace />exportHamzaBtn" 
					class="btn btn-primary btn-sm"
					style="display: none;"
				>
					Export data for Hamza
				</button>
			</div>
		</div>
		<div id="<portlet:namespace />bookingsTableWrapper">
			<table class="table table-bordered table-striped" id="<portlet:namespace />bookingsTable" style="width:100%">
				<thead>
					<tr>
						<th>Booking ID</th>
						<th>Email</th>
						<th>Test Type</th>
						<th>Test Date</th>
						<th>Booking Status</th>
						<th>Test Username</th>
						<th>Test Password</th>
						<th>Test Link</th>
					</tr>
				</thead>
				<tbody id="<portlet:namespace />bookingsTableBody">
				</tbody>
			</table>
		</div>
	</div>
		</div>
		<!-- End Bookings Content -->

		<!-- CSV Upload Section -->
		<div class="mt-5">
			<h4>Import Bookings from CSV</h4>
			<div class="card">
				<div class="card-body">
					<form id="<portlet:namespace />csvUploadForm" enctype="multipart/form-data">
						<div class="form-group">
							<label for="<portlet:namespace />csvFile">Select CSV File</label>
							<input 
								type="file" 
								class="form-control-file" 
								id="<portlet:namespace />csvFile" 
								name="csvFile"
								accept=".csv"
								required
							/>
							<small class="form-text text-muted">
								Expected format: Booking ID, Email, Test Type, Test Date, Booking Status, Test ID, Test Username, Test Password, Test Link
							</small>
						</div>
						<button type="submit" class="btn btn-primary">
							<span class="btn-text">Submit</span>
							<span class="spinner-border spinner-border-sm ml-2" role="status" aria-hidden="true" style="display: none;"></span>
						</button>
						<button type="button" class="btn btn-secondary ml-2" id="<portlet:namespace />clearCsvBtn" style="display: none;">Clear</button>
					</form>
				</div>
			</div>

			<!-- CSV Records Display -->
			<div id="<portlet:namespace />csvRecordsWrapper" style="display: none; margin-top: 20px;">
				<h5>Imported Records</h5>
				<div class="table-responsive">
					<table class="table table-bordered table-striped" id="<portlet:namespace />csvRecordsTable">
						<thead class="thead-light">
							<tr>
								<th>Booking ID</th>
								<th>Email</th>
								<th>Test Type</th>
								<th>Test Date</th>
								<th>Booking Status</th>
								<th>Test ID</th>
								<th>Test Username</th>
								<th>Test Password</th>
								<th>Test Link</th>
								<th>Update Status</th>
							</tr>
						</thead>
						<tbody id="<portlet:namespace />csvRecordsBody">
						</tbody>
					</table>
				</div>
				<div class="alert alert-info mt-3">
					<strong>Total Records:</strong> <span id="<portlet:namespace />totalRecords">0</span>
				</div>
			</div>
		</div>
		<!-- End CSV Upload Section -->

	</div>
	<!-- End Tab Content -->

	<script>
		(function () {
			var highlightedDates = <%= testDatesJson %>; // e.g. ["2025-12-06", ...]
			var testsData = <%= safeTestsJson %>; // Store all tests data
			var namespace = '<portlet:namespace />';
			var currentDate = new Date();
			var currentMonth = currentDate.getMonth();
			var currentYear = currentDate.getFullYear();
			var selectedDate = null;
			var bookingsDataTable = null;
			var currentBookings = [];
			var currentTestsForDate = [];

			var monthNames = [
				'January', 'February', 'March', 'April', 'May', 'June',
				'July', 'August', 'September', 'October', 'November', 'December'
			];

			function formatDate(date) {
				var year = date.getFullYear();
				var month = String(date.getMonth() + 1).padStart(2, '0');
				var day = String(date.getDate()).padStart(2, '0');
				return year + '-' + month + '-' + day;
			}

			function formatDateDDMMYYYY(dateString) {
				if (!dateString) return '';
				
				// Handle ISO format (YYYY-MM-DD or YYYY-MM-DDTHH:mm:ss...)
				var dateOnly = dateString.substring(0, 10);
				var parts = dateOnly.split('-');
				
				if (parts.length === 3) {
					// Convert from YYYY-MM-DD to DD/MM/YYYY
					return parts[2] + '/' + parts[1] + '/' + parts[0];
				}
				
				return dateString; // Return as-is if format is unexpected
			}

			function renderCalendar() {
				var firstDay = new Date(currentYear, currentMonth, 1);
				var lastDay = new Date(currentYear, currentMonth + 1, 0);
				var startingDayOfWeek = firstDay.getDay();
				var daysInMonth = lastDay.getDate();

				var calendarBody = document.getElementById(namespace + 'calendarBody');
				var monthYearDisplay = document.getElementById(namespace + 'currentMonthYear');

				if (!calendarBody || !monthYearDisplay) {
					return;
				}

				monthYearDisplay.textContent = monthNames[currentMonth] + ' ' + currentYear;
				calendarBody.innerHTML = '';

				var date = 1;
				var rows = Math.ceil((daysInMonth + startingDayOfWeek) / 7);

				for (var i = 0; i < rows; i++) {
					var row = document.createElement('tr');

					for (var j = 0; j < 7; j++) {
						var cell = document.createElement('td');
						cell.className = 'test-calendar-day';

						if (i === 0 && j < startingDayOfWeek) {
							cell.innerHTML = '';
						}
						else if (date > daysInMonth) {
							cell.innerHTML = '';
						}
						else {
							var cellDate = new Date(currentYear, currentMonth, date);
							var dateStr = formatDate(cellDate);
							var isHighlighted = highlightedDates.indexOf(dateStr) !== -1;

							cell.innerHTML =
								'<span class=\"' + (isHighlighted ? 'test-date-highlight' : '') +
								'\">' + date + '</span>';

							cell.setAttribute('data-date', dateStr);

							if (isHighlighted) {
								cell.classList.add('has-test');
								cell.style.cursor = 'pointer';

								// Use the cell's own data-date attribute so each
								// click handler uses the correct date, avoiding
								// any closure issues with loop variables.
								cell.addEventListener('click', function() {
									var clickedDate = this.getAttribute('data-date');
									handleDateClick(clickedDate);
								});
							}

							date++;
						}

						row.appendChild(cell);
					}

					calendarBody.appendChild(row);
				}
			}

			function initCalendar() {
				var prevBtn = document.getElementById(namespace + 'prevMonth');
				var nextBtn = document.getElementById(namespace + 'nextMonth');

				if (prevBtn) {
					prevBtn.addEventListener('click', function () {
						currentMonth--;

						if (currentMonth < 0) {
							currentMonth = 11;
							currentYear--;
						}

						renderCalendar();
					});
				}

				if (nextBtn) {
					nextBtn.addEventListener('click', function () {
						currentMonth++;

						if (currentMonth > 11) {
							currentMonth = 0;
							currentYear++;
						}

						renderCalendar();
					});
				}

				renderCalendar();
			}

			function handleDateClick(dateStr) {
				selectedDate = dateStr;
				
				// Remove previous selection
				var prevSelectedCells = document.querySelectorAll('.selected-date');
				prevSelectedCells.forEach(function(cell) {
					cell.classList.remove('selected-date');
				});
				
				// Highlight selected date
				var cells = document.querySelectorAll('[data-date="' + dateStr + '"]');
				cells.forEach(function(cell) {
					cell.classList.add('selected-date');
				});

				// Find all tests for this date
				var testsForDate = [];
				if (testsData && testsData.items) {
					for (var i = 0; i < testsData.items.length; i++) {
						var test = testsData.items[i];
						var testDateRaw = test.testDate;
						
						if (testDateRaw) {
							var testDateOnly = testDateRaw.substring(0, 10); // Extract YYYY-MM-DD
							if (testDateOnly === dateStr) {
								testsForDate.push(test);
							}
						}
					}
				}

				// Store current tests for export
				currentTestsForDate = testsForDate;

				// Update title
				var bookingsTitle = document.getElementById(namespace + 'bookingsTitle');
				if (bookingsTitle) {
					var formattedDate = formatDateDDMMYYYY(dateStr);
					bookingsTitle.textContent = 'Bookings for ' + formattedDate + ' (' + testsForDate.length + ' test(s))';
				}

				// Fetch bookings for all tests on this date
				fetchBookingsForTests(testsForDate);
			}

			function fetchBookingsForTests(tests) {
				var bookingsTable = jQuery('#' + namespace + 'bookingsTable');
				if (!bookingsTable.length) return;

				// Destroy existing DataTable if it exists
				if (bookingsDataTable) {
					bookingsDataTable.destroy();
					bookingsDataTable = null;
				}

				// Clear table and show loading (plain HTML, no DataTable yet)
				bookingsTable.find('tbody').html(
					'<tr><td colspan="8" class="text-center">Loading bookings...</td></tr>'
				);

				if (tests.length === 0) {
					// If there are no tests on this date, just show a message and skip DataTables
					bookingsTable.find('tbody').html(
						'<tr><td colspan="8" class="text-center text-muted">No tests found for this date</td></tr>'
					);
					return;
				}

				var allBookings = [];
				var completedRequests = 0;

				tests.forEach(function(test) {
					var testId = test.id;
					var testType = test.typeOfTheTest ? (test.typeOfTheTest.name || '') : '';
					var testDate = test.testDate || '';

					var resourceURL = '<%= resourceURLString %>&<portlet:namespace />testId=' + testId;

					fetch(resourceURL)
						.then(function(response) {
							return response.json();
						})
						.then(function(data) {
							if (data.items && data.items.length > 0) {
								data.items.forEach(function(booking) {
									booking.testType = testType;
									booking.testDate = testDate;
									allBookings.push(booking);
								});
							}

							completedRequests++;

							if (completedRequests === tests.length) {
								displayBookings(allBookings);
							}
						})
						.catch(function(error) {
							console.error('Error fetching bookings for test ' + testId + ':', error);
							completedRequests++;

							if (completedRequests === tests.length) {
								displayBookings(allBookings);
							}
						});
				});
			}

			function displayBookings(bookings) {
				var bookingsTable = jQuery('#' + namespace + 'bookingsTable');
				var bookingsTbody = jQuery('#' + namespace + 'bookingsTableBody');

				if (!bookingsTable.length || !bookingsTbody.length) {
					return;
				}

				// Destroy existing DataTable if it exists so we can safely rebuild rows
				if (bookingsDataTable) {
					bookingsDataTable.destroy();
					bookingsDataTable = null;
				}

				// Store current bookings for export
				currentBookings = bookings;

				// Show/hide export buttons
				var exportBtn = document.getElementById(namespace + 'exportCsvBtn');
				if (exportBtn) {
					exportBtn.style.display = bookings.length > 0 ? 'inline-block' : 'none';
				}
				var exportHamzaBtn = document.getElementById(namespace + 'exportHamzaBtn');
				if (exportHamzaBtn) {
					exportHamzaBtn.style.display = bookings.length > 0 ? 'inline-block' : 'none';
				}

				if (bookings.length === 0) {
					// Plain HTML message, no DataTables when there's no data
					bookingsTbody.html(
						'<tr><td colspan="8" class="text-center text-muted">No bookings found for this date</td></tr>'
					);
					return;
				}

				// Build table rows in DOM
				bookingsTbody.empty();

				bookings.forEach(function(booking) {
					var bookingId = booking.id || '';
					var testPassword = booking.testPassword || '';

					var email = booking.emailId || '';
					var testType = booking.testType || '';
					var testDate = booking.testDate || '';
					var formattedTestDate = formatDateDDMMYYYY(testDate);
					var bookingStatus = booking.testBookingStatus
						? (booking.testBookingStatus.name || booking.testBookingStatus.key || '')
						: '';
					var testUsername = booking.testUsername || '';
					var testLink = booking.testLink || '';

					var rowHtml =
						'<tr>' +
							'<td>' + escapeHtml(String(bookingId)) + '</td>' +
							'<td>' + escapeHtml(email) + '</td>' +
							'<td>' + escapeHtml(testType) + '</td>' +
							'<td>' + escapeHtml(formattedTestDate) + '</td>' +
							'<td>' + escapeHtml(bookingStatus) + '</td>' +
							'<td>' + escapeHtml(testUsername) + '</td>' +
							'<td>' + escapeHtml(testPassword) + '</td>' +
							'<td>' + escapeHtml(testLink) + '</td>' +
						'</tr>';

					bookingsTbody.append(rowHtml);
				});

				// Now turn the fully-built table into a DataTable
				bookingsDataTable = bookingsTable.DataTable({
					"paging": true,
					"pageLength": 10,
					"searching": true,
					"ordering": true,
					"info": true,
					"autoWidth": false,
					"language": {
						"emptyTable": "No bookings found for this date",
						"zeroRecords": "No matching bookings found"
					}
				});
			}

			function escapeHtml(text) {
				if (!text) return '';
				var div = document.createElement('div');
				div.textContent = text;
				return div.innerHTML;
			}

			function handleExportCSV() {
				if (!selectedDate || currentTestsForDate.length === 0) {
					alert('Please select a date with bookings to export.');
					return;
				}

				// Build export URL with selected date and test IDs
				var testIds = [];
				currentTestsForDate.forEach(function(test) {
					testIds.push(test.id);
				});

				var exportURL = '<%= exportCsvURLString %>';
				exportURL += '&<portlet:namespace />selectedDate=' + encodeURIComponent(selectedDate);
				
				// Add test IDs as parameters
				testIds.forEach(function(testId, index) {
					exportURL += '&<portlet:namespace />testIds=' + encodeURIComponent(testId);
				});

				// Trigger download
				window.location.href = exportURL;
			}

			function handleExportHamza() {
				if (!selectedDate || currentTestsForDate.length === 0) {
					alert('Please select a date with bookings to export.');
					return;
				}

				// Build export URL with selected date and test IDs
				var testIds = [];
				currentTestsForDate.forEach(function(test) {
					testIds.push(test.id);
				});

				var exportURL = '<%= exportHamzaURLString %>';
				exportURL += '&<portlet:namespace />selectedDate=' + encodeURIComponent(selectedDate);
				
				// Add test IDs as parameters
				testIds.forEach(function(testId, index) {
					exportURL += '&<portlet:namespace />testIds=' + encodeURIComponent(testId);
				});

				// Trigger download
				window.location.href = exportURL;
			}

			// Attach click handlers to export buttons after DOM is ready
			if (typeof Liferay !== 'undefined') {
				Liferay.on('allPortletsReady', function() {
					var exportBtn = document.getElementById(namespace + 'exportCsvBtn');
					if (exportBtn) {
						exportBtn.addEventListener('click', handleExportCSV);
					}
					var exportHamzaBtn = document.getElementById(namespace + 'exportHamzaBtn');
					if (exportHamzaBtn) {
						exportHamzaBtn.addEventListener('click', handleExportHamza);
					}
				});
			}
			else {
				// Fallback if Liferay is not available
				setTimeout(function() {
					var exportBtn = document.getElementById(namespace + 'exportCsvBtn');
					if (exportBtn) {
						exportBtn.addEventListener('click', handleExportCSV);
					}
					var exportHamzaBtn = document.getElementById(namespace + 'exportHamzaBtn');
					if (exportHamzaBtn) {
						exportHamzaBtn.addEventListener('click', handleExportHamza);
					}
				}, 500);
			}

			if (typeof Liferay !== 'undefined') {
				Liferay.on('allPortletsReady', initCalendar);
			}
			else {
				initCalendar();
			}
		})();

		// CSV Upload Handler
		(function() {
			var namespace = '<portlet:namespace />';
			var csvForm = document.getElementById(namespace + 'csvUploadForm');
			var csvFileInput = document.getElementById(namespace + 'csvFile');
			var csvRecordsWrapper = document.getElementById(namespace + 'csvRecordsWrapper');
			var csvRecordsBody = document.getElementById(namespace + 'csvRecordsBody');
			var totalRecordsSpan = document.getElementById(namespace + 'totalRecords');
			var clearBtn = document.getElementById(namespace + 'clearCsvBtn');
			var uploadURL = '<%= uploadCsvURLString %>';

			function escapeHtml(text) {
				if (!text) return '';
				var div = document.createElement('div');
				div.textContent = text;
				return div.innerHTML;
			}

			function showSpinner(show) {
				var btnText = csvForm.querySelector('.btn-text');
				var spinner = csvForm.querySelector('.spinner-border');
				var submitBtn = csvForm.querySelector('button[type="submit"]');
				
				if (show) {
					btnText.textContent = 'Processing...';
					spinner.style.display = 'inline-block';
					submitBtn.disabled = true;
				} else {
					btnText.textContent = 'Submit';
					spinner.style.display = 'none';
					submitBtn.disabled = false;
				}
			}

			function displayCSVRecords(data) {
				// Clear previous records
				csvRecordsBody.innerHTML = '';
				
				if (!data.records || data.records.length === 0) {
					csvRecordsBody.innerHTML = '<tr><td colspan="10" class="text-center text-muted">No valid records found in CSV file</td></tr>';
					totalRecordsSpan.textContent = '0';
					csvRecordsWrapper.style.display = 'block';
					clearBtn.style.display = 'inline-block';
					return;
				}
				
				// Add each record as a table row
				data.records.forEach(function(record) {
					var row = document.createElement('tr');
					var updateStatus = record.updateStatus || 'success';
					var statusClass = updateStatus === 'success' ? 'text-success' : 'text-danger';
					var iconName = updateStatus === 'success' ? 'check-circle' : 'times-circle';
					var statusIcon = '<svg class="lexicon-icon lexicon-icon-' + iconName + ' ' + statusClass + '" style="width: 16px; height: 16px; vertical-align: middle;">' +
						'<use href="/o/classic-theme/images/clay/icons.svg#' + iconName + '" />' +
						'</svg>';
					
					row.innerHTML = 
						'<td>' + escapeHtml(record.bookingId) + '</td>' +
						'<td>' + escapeHtml(record.email) + '</td>' +
						'<td>' + escapeHtml(record.testType) + '</td>' +
						'<td>' + escapeHtml(record.testDate) + '</td>' +
						'<td>' + escapeHtml(record.bookingStatus) + '</td>' +
						'<td>' + escapeHtml(record.testId) + '</td>' +
						'<td>' + escapeHtml(record.testUsername) + '</td>' +
						'<td>' + escapeHtml(record.testPassword) + '</td>' +
						'<td>' + escapeHtml(record.testLink) + '</td>' +
						'<td class="' + statusClass + '">' + statusIcon + ' ' + escapeHtml(updateStatus) + '</td>';
					csvRecordsBody.appendChild(row);
				});
				
				// Update total count and show results
				totalRecordsSpan.textContent = data.totalRecords;
				csvRecordsWrapper.style.display = 'block';
				clearBtn.style.display = 'inline-block';
				
				// Scroll to results
				csvRecordsWrapper.scrollIntoView({ behavior: 'smooth', block: 'start' });
			}

			function handleFormSubmit(e) {
				e.preventDefault();
				
				var file = csvFileInput.files[0];
				
				if (!file) {
					alert('Please select a CSV file');
					return;
				}
				
				if (!file.name.endsWith('.csv')) {
					alert('Please select a valid CSV file');
					return;
				}
				
				// Create FormData to send file
				var formData = new FormData();
				formData.append('csvFile', file);
				
				// Show loading state
				showSpinner(true);
				
				// Submit to server
				fetch(uploadURL, {
					method: 'POST',
					body: formData
				})
				.then(function(response) {
					return response.json();
				})
				.then(function(data) {
					showSpinner(false);
					
					if (data.error) {
						alert('Error processing CSV: ' + data.error);
						return;
					}
					
					if (data.success) {
						displayCSVRecords(data);
						console.log('CSV Upload successful. Total records: ' + data.totalRecords);
						console.log('All records have been printed to the server console/logs.');
					}
				})
				.catch(function(error) {
					showSpinner(false);
					console.error('Error uploading CSV:', error);
					alert('Error uploading file. Please try again.');
				});
			}

			function handleClear() {
				// Clear form
				csvForm.reset();
				
				// Hide results
				csvRecordsWrapper.style.display = 'none';
				csvRecordsBody.innerHTML = '';
				totalRecordsSpan.textContent = '0';
				clearBtn.style.display = 'none';
			}

			// Attach event listeners
			if (csvForm) {
				csvForm.addEventListener('submit', handleFormSubmit);
			}
			
			if (clearBtn) {
				clearBtn.addEventListener('click', handleClear);
			}
		})();
	</script>

	<style>
		.calendar-bookings-wrapper {
			display: flex;
			flex-direction: column;
			gap: 20px;
			margin-bottom: 20px;
		}

		.test-calendar-container {
			width: 100%;
		}

		.bookings-container {
			width: 100%;
		}

		.bookings-container h5 {
			margin-bottom: 15px;
			font-weight: bold;
		}

		.test-calendar-header {
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin-bottom: 10px;
		}

		.test-calendar-title {
			margin: 0;
			flex: 1;
			text-align: center;
		}

		.test-calendar-table {
			width: 100%;
			table-layout: fixed;
		}

		.test-calendar-table th {
			text-align: center;
			background-color: #f5f5f5;
			font-weight: bold;
			padding: 8px;
		}

		.test-calendar-day {
			text-align: center;
			padding: 10px;
			height: 50px;
			vertical-align: middle;
		}

		.test-calendar-day span {
			display: inline-block;
			width: 30px;
			height: 30px;
			line-height: 30px;
			border-radius: 50%;
		}

		.test-calendar-day.has-test span.test-date-highlight {
			background-color: #ffeb3b;
			color: #000;
			font-weight: bold;
		}

		.test-calendar-day.has-test:hover {
			background-color: #e8e8e8;
		}

		.test-calendar-day.selected-date {
			background-color: #4CAF50 !important;
		}

		.test-calendar-day.selected-date span {
			background-color: #2E7D32 !important;
			color: #fff !important;
		}

		.bookings-container table {
			width: 100%;
			font-size: 14px;
		}

		.bookings-container table th {
			background-color: #f5f5f5;
			font-weight: bold;
			padding: 10px;
			text-align: left;
		}

		.bookings-container table td {
			padding: 8px;
			vertical-align: middle;
		}

		.nav-tabs {
			border-bottom: 2px solid #dee2e6;
			margin-bottom: 20px;
		}

		.nav-tabs .nav-link {
			color: #495057;
			border: 1px solid transparent;
			border-top-left-radius: 0.25rem;
			border-top-right-radius: 0.25rem;
		}

		.nav-tabs .nav-link:hover {
			border-color: #e9ecef #e9ecef #dee2e6;
			isolation: isolate;
		}

		.nav-tabs .nav-link.active {
			color: #495057;
			background-color: #fff;
			border-color: #dee2e6 #dee2e6 #fff;
		}

		.tab-content {
			padding-top: 10px;
		}

		/* CSV Upload Section Styles */
		.mt-5 {
			margin-top: 3rem;
		}

		.mt-3 {
			margin-top: 1rem;
		}

		.ml-2 {
			margin-left: 0.5rem;
		}

		.card {
			border: 1px solid #dee2e6;
			border-radius: 0.25rem;
			box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
			background-color: #fff;
		}

		.card-body {
			padding: 1.25rem;
		}

		.form-group {
			margin-bottom: 1rem;
		}

		.form-group label {
			font-weight: bold;
			margin-bottom: 0.5rem;
			display: block;
		}

		.form-control-file {
			display: block;
			padding: 0.375rem 0;
		}

		.btn {
			display: inline-block;
			padding: 0.375rem 0.75rem;
			font-size: 1rem;
			border-radius: 0.25rem;
			cursor: pointer;
			border: 1px solid transparent;
			text-align: center;
			vertical-align: middle;
			user-select: none;
			line-height: 1.5;
			transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out;
		}

		.btn:disabled {
			opacity: 0.65;
			cursor: not-allowed;
		}

		.btn-primary {
			color: #fff;
			background-color: #007bff;
			border-color: #007bff;
		}

		.btn-primary:hover:not(:disabled) {
			background-color: #0056b3;
			border-color: #0056b3;
		}

		.btn-secondary {
			color: #fff;
			background-color: #6c757d;
			border-color: #6c757d;
		}

		.btn-secondary:hover {
			background-color: #5a6268;
			border-color: #545b62;
		}

		.table-responsive {
			overflow-x: auto;
			-webkit-overflow-scrolling: touch;
		}

		.alert-info {
			background-color: #d1ecf1;
			border-color: #bee5eb;
			color: #0c5460;
			padding: 0.75rem 1.25rem;
			border-radius: 0.25rem;
			border: 1px solid transparent;
		}

		.text-center {
			text-align: center;
		}

		.text-muted {
			color: #6c757d;
		}

		.text-success {
			color: #28a745;
			font-weight: bold;
		}

		.text-danger {
			color: #dc3545;
			font-weight: bold;
		}

		.form-text {
			display: block;
			margin-top: 0.25rem;
		}

		.spinner-border {
			display: inline-block;
			width: 1rem;
			height: 1rem;
			vertical-align: text-bottom;
			border: 0.15em solid currentColor;
			border-right-color: transparent;
			border-radius: 50%;
			animation: spinner-border 0.75s linear infinite;
		}

		.spinner-border-sm {
			width: 0.75rem;
			height: 0.75rem;
			border-width: 0.1em;
		}

		@keyframes spinner-border {
			to { transform: rotate(360deg); }
		}

	</style>

	<!-- <h4>Tests</h4>

	<liferay-ui:search-container
		delta="20"
		total="<%= testItems.size() %>"
		emptyResultsMessage="no-tests-found">

		<liferay-ui:search-container-results results="<%= testItems %>" />

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.json.JSONObject"
			modelVar="test">

			<%
				long id = test.getLong("id");
				String testDate = test.getString("testDate");

				JSONObject startTime = test.getJSONObject("startTime");
				JSONObject endTime = test.getJSONObject("endTime");
				JSONObject typeOfTheTest = test.getJSONObject("typeOfTheTest");
				JSONObject testStatus = test.getJSONObject("testStatus");

				int capacity = test.getInt("capacity");
				String testCenterERC = test.getString("testCenterERC");
			%>

			<liferay-ui:search-container-column-text
				name="ID"
				value="<%= String.valueOf(id) %>"
			/>

			<liferay-ui:search-container-column-text
				name="Test Date"
				value="<%= testDate %>"
			/>

			<liferay-ui:search-container-column-text
				name="Start Time"
				value="<%= (startTime != null) ? startTime.getString(\"name\") : \"\" %>"
			/>

			<liferay-ui:search-container-column-text
				name="End Time"
				value="<%= (endTime != null) ? endTime.getString(\"name\") : \"\" %>"
			/>

			<liferay-ui:search-container-column-text
				name="Type"
				value="<%= (typeOfTheTest != null) ? typeOfTheTest.getString(\"name\") : \"\" %>"
			/>

			<liferay-ui:search-container-column-text
				name="Status"
				value="<%= (testStatus != null) ? testStatus.getString(\"name\") : \"\" %>"
			/>

			<liferay-ui:search-container-column-text
				name="Capacity"
				value="<%= String.valueOf(capacity) %>"
			/>

			<liferay-ui:search-container-column-text
				name="Test Center ERC"
				value="<%= testCenterERC %>"
			/>

		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />

	</liferay-ui:search-container> -->

</c:if>

<c:if test="<%= (noRecords != null && noRecords) || testItems.isEmpty() %>">
	<div class="alert alert-info" role="alert" style="margin: 20px 0; padding: 20px; text-align: center;">
		<h4 style="margin-bottom: 10px;">
			<liferay-ui:message key="no-records-found" />
		</h4>
		<p style="margin: 0; font-size: 16px;">
			<%= noRecordsMessage %>
		</p>
	</div>
</c:if>


