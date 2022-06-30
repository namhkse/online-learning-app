const COLORS = [
    '#FF6633', '#FFB399', '#FF33FF', '#FFFF99', '#00B3E6',
    '#E6B333', '#3366E6', '#999966', '#99FF99', '#B34D4D',
    '#80B300', '#809900', '#E6B3B3', '#6680B3', '#66991A',
    '#FF99E6', '#CCFF1A', '#FF1A66', '#E6331A', '#33FFCC',
    '#66994D', '#B366CC', '#4D8000', '#B33300', '#CC80CC',
    '#66664D', '#991AFF', '#E666FF', '#4DB3FF', '#1AB399',
    '#E666B3', '#33991A', '#CC9999', '#B3B31A', '#00E680',
    '#4D8066', '#809980', '#E6FF80', '#1AFF33', '#999933',
    '#FF3380', '#CCCC00', '#66E64D', '#4D80CC', '#9900B3',
    '#E64D66', '#4DB380', '#FF4D4D', '#99E6E6', '#6666FF'
];
const blogTrendCtx = document.getElementById('myChart').getContext('2d');
const blogTrendChart = new Chart(blogTrendCtx, {
    type: 'bar',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
                label: '# of Views',
                data: [12, 19, 3, 5, 2, 3],
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        },
        plugins: {
            title: {
                display: true,
                text: 'Blog Category Trend Chart',
                position: 'bottom',
                font: {
                    size: 16
                }
            }
        }
    }
});

//Show amount registration chart by date
const registrationCtx = document.getElementById('registrationChart').getContext('2d');
const registrationChart = new Chart(registrationCtx, {
    type: 'line',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
                label: '# of Registration',
                data: [12, 19, 3, 5, 2, 3],
                backgroundColor: '#6f42c1',
                borderColor: '#6610f2',
                borderWidth: 1,
                tension: 0.1
            }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        },
        plugins: {
            title: {
                display: true,
                text: 'Registration Chart',
                position: 'bottom',
                font: {
                    size: 16
                }
            }
        }
    }
});

//Show subjects and  amount course in each subject
const subjectCtx = document.getElementById("subjectChart").getContext('2d');
const subjectChart = new Chart(subjectCtx, {
    type: 'doughnut',
    data: {
        labels: [
            'Red',
            'Blue',
            'Yellow',
        ],
        datasets: [{
                label: '',
                data: [300, 50, 100],
                backgroundColor: COLORS,
                borderColor: '#f8f9fa',
                hoverOffset: 4
            }]
    },
    options: {
        plugins: {
            title: {
                display: true,
                text: 'Subject & Course Chart',
                position: 'bottom',
                font: {
                    size: 16
                }
            },
            legend: {
                position: 'bottom'
            }
        }
    }
});

// Show posiblity an account will enrolled courses belong to a subject
const subjectEnrollTrendCtx = document.getElementById("subjectEnrollTrend").getContext('2d');
const subjectEnrollTrend = new Chart(subjectEnrollTrendCtx, {
    type: 'radar',
    data: {
        labels: [],
        datasets: [{
                label: 'The probability that a user enrolls in a course on this subject',
                data: [],
                fill: true,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgb(255, 99, 132)',
                pointBackgroundColor: 'rgb(255, 99, 132)',
                pointBorderColor: '#fff',
                pointHoverBackgroundColor: '#fff',
                pointHoverBorderColor: 'rgb(255, 99, 132)'
            }]
    },
    options: {
        plugins: {
            legend: {
                display: false
            },
            title: {
                display: true,
                text: 'Possibly Interested In A Subject Of An Account',
                position: 'bottom',
                font: {
                    size: 16
                }
            }
        }
    }
});

// Show revenue in each month
const revenueCtx = document.getElementById('revenueChart').getContext('2d');
const revenueChart = new Chart(revenueCtx, {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
                data: [],
                label: "Income",
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 3

            }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true,
                ticks: {
                    callback: function (value, index, values) {
                        if (parseInt(value) >= 1000) {
                            return '$' + value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        } else {
                            return '$' + value;
                        }
                    }
                }
            }
        },
        plugins: {
            legend: {
                display: false
            }
        }
    }
});

// Show revenue of each subject
const subjectRevenueCtx = document.getElementById('subjectRevenueChart').getContext('2d');
const subjectRevenueChart = new Chart(subjectRevenueCtx, {
    type: 'bar',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
                label: '',
                data: [12, 19, 3, 5, 2, 3],
                backgroundColor: '#0d6efd',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true,
                ticks: {
                    callback: function (value, index, values) {
                        if (parseInt(value) >= 1000) {
                            return '$' + value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        } else {
                            return '$' + value;
                        }
                    }
                }
            }
        },
        plugins: {
            title: {
                display: true,
                text: 'Subject Revenue Chart',
                position: 'bottom',
                font: {
                    size: 14
                }
            }
        }
    }
});

function drawCourseSubjectChart() {
    fetch("../api/statistics/coursesubject")
            .then(resp => resp.json())
            .then(data => {
                let subjects = data.map(e => e.subject)
                let amountCourseInEachSubject = data.map(e => e.amount);
                subjectChart.data.labels = subjects;
                subjectChart.data.datasets[0].data = amountCourseInEachSubject;
                subjectChart.update();
            })
            .catch(error => console.log(error))
}

function drawCourseEnrollTable() {
    let tableContent = document.getElementById("courseEnrollTable").querySelector("tbody");
    fetch("../api/statistics/enrollcourse")
            .then(resp => resp.json())
            .then(data => {
                let n = 1;
                for (let course of data) {
                    let row = document.createElement("tr");
                    let courseIdCell = document.createElement("td");
                    courseIdCell.textContent = n++;
                    let courseNameCell = document.createElement("td");
                    courseNameCell.textContent = course.courseName;
                    let numberOfEnrollCell = document.createElement("td");
                    numberOfEnrollCell.textContent = course.numberOfEnroll;

                    row.appendChild(courseIdCell);
                    row.appendChild(courseNameCell);
                    row.appendChild(numberOfEnrollCell);

                    tableContent.appendChild(row);
                }
            })
            .catch(error => console.log(error))
}


function drawRevenueChart(m1, y1, m2, y2) {
    let url = (m1 && y1 && m2 && y2)
            ? `../api/statistics/revenue/${m1}/${y1}/${m2}/${y2}`
            : "../api/statistics/revenue";

    fetch(url)
            .then(resp => resp.json())
            .then(data => {
                let monthInYear = data.map(e => e.month + "/" + e.year);
                let revenues = data.map(e => e.revenue);
                revenueChart.data.labels = monthInYear;
                revenueChart.data.datasets[0].data = revenues;
                revenueChart.update();
            })
            .catch(error => console.log(error));
}

function drawBlogTrendChart() {
    fetch("../api/statistics/blogcategorytrend")
            .then(resp => resp.json())
            .then(data => {
                let blogCategories = data.map(e => e.blogCategoryName);
                let amountViews = data.map(e => e.numberOfView);
                blogTrendChart.data.labels = blogCategories;
                blogTrendChart.data.datasets[0].data = amountViews;
                blogTrendChart.update();
            })
            .catch(error => console.log(error));
}

function drawSubjectEnrollTrend() {
    fetch("../api/statistics/amount_account_subject")
            .then(resp => resp.json())
            .then(data => {
                let subjectNames = data.map(e => e.subjectName);
                let amountEnrolled = data.map(e => e.amountEnrolled / e.totalAccount);
                subjectEnrollTrend.data.labels = subjectNames;
                subjectEnrollTrend.data.datasets[0].data = amountEnrolled;
                subjectEnrollTrend.update();
            })
            .catch(error => console.log(error));
}

function drawRegistrationChart(from, to) {
    fetch(`../api/statistics/registration/${from}/${to}`)
            .then(resp => resp.json())
            .then(data => {
                let dates = data.map(e => `${e.date.year}-${e.date.month}-${e.date.day}`);
                let amounts = data.map(e => e.amount);
                registrationChart.data.labels = dates;
                registrationChart.data.datasets[0].data = amounts;
                registrationChart.update();
            })
            .catch(error => console.log(error));
}

const fromMonthInput = document.getElementById("fromMonth");
const toMonthInput = document.getElementById("toMonth");

function searchRevenueEventHandler() {
    let str1 = fromMonthInput.value;
    let str2 = toMonthInput.value;
    const pattern = /^(\d?\d)(\/\d{4})$/;

    const styleInvalidInput = (input, borderStyle) => input.style.borderRight = borderStyle;
    let invalidBorderStyle = "5px red solid";

    styleInvalidInput(fromMonthInput, "");
    styleInvalidInput(toMonthInput, "");


    if (!pattern.test(str1)) {
        styleInvalidInput(fromMonthInput, invalidBorderStyle);
        return;
    }

    if (!pattern.test(str2)) {
        styleInvalidInput(toMonthInput, invalidBorderStyle);
        return;
    }

    let tokens = str1.split("/");
    let m1 = parseInt(tokens[0]);
    let y1 = parseInt(tokens[1]);
    tokens = str2.split("/");
    let m2 = parseInt(tokens[0]);
    let y2 = parseInt(tokens[1]);

    if (m1 > 12) {
        styleInvalidInput(fromMonthInput, invalidBorderStyle);
        return;
    }

    if (m2 > 12) {
        styleInvalidInput(toMonthInput, invalidBorderStyle);
        return;
    }

    drawRevenueChart(m1, y1, m2, y2);
}

/**
 * Draw revenue of each subject. Input is date string in format yyyy-M-d.
 * @param {string} from date. If it is null or undefined draw revenue so far.
 * @param {string} to date. If it is null or undefined draw revenue so far.
 */
function drawSubjectRevenueChart(from, to) {
    let url = "../api/statistics/revenue/subject";

    if (from && to) {
        url = url + "/" + from + "/" + to;

    }

    fetch(url)
            .then(resp => resp.json())
            .then(data => {
                let subjectNames = data.map(e => e.subjectName);
                let revenues = data.map(e => e.revenue);
                subjectRevenueChart.data.labels = subjectNames;
                subjectRevenueChart.data.datasets[0].data = revenues;
                subjectRevenueChart.update();
            })
            .catch(error => console.log(error));
}

/**
 * Format Date to string in ISO_LOCAL_DATE
 * @param {type} date
 * @returns {String} 
 */
function formatToISO_LOCAL_DATE(date) {
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();
    return `${year}-${month < 10 ? "0" : ""}${month}-${day < 10 ? "0" : ""}${day}`;
}

// Start - config filter registration by date event.
$("#fromDateRegistration").change(function () {
    drawRegistrationChart($("#fromDateRegistration").val(), $("#toDateRegistration").val());
});

$("#toDateRegistration").change(function () {
    drawRegistrationChart($("#fromDateRegistration").val(), $("#toDateRegistration").val());
});
// End - config filter registration by date event.

// This event belong to filter revenue by month
fromMonthInput.addEventListener('change', () => searchRevenueEventHandler());
toMonthInput.addEventListener('change', () => searchRevenueEventHandler());

let today = new Date();
let previous7Day = new Date(today);
previous7Day.setDate(today.getDate() - 7);

//Set default fetch data before 7 days
$("#fromDateRegistration").val(formatToISO_LOCAL_DATE(previous7Day));
$("#toDateRegistration").val(formatToISO_LOCAL_DATE(today));
drawRegistrationChart(formatToISO_LOCAL_DATE(previous7Day), formatToISO_LOCAL_DATE(today));

//Start - config event search in Subject Revenue Chart
$("#fromDateRevenueSubject").change(function () {
    drawSubjectRevenueChart($("#fromDateRevenueSubject").val(), $("#toDateRevenueSubject").val());
});

$("#toDateRevenueSubject").change(function () {
    drawSubjectRevenueChart($("#fromDateRevenueSubject").val(), $("#toDateRevenueSubject").val());
});
//End - config event search in Subject Revenue Chart

//Set default fetch data from previous 7 days
$("#fromDateRevenueSubject").val(formatToISO_LOCAL_DATE(previous7Day));
$("#toDateRevenueSubject").val(formatToISO_LOCAL_DATE(today));
drawSubjectRevenueChart(formatToISO_LOCAL_DATE(previous7Day), formatToISO_LOCAL_DATE(today));


drawSubjectEnrollTrend();
drawBlogTrendChart();
drawCourseSubjectChart();
drawCourseEnrollTable();
drawRevenueChart();