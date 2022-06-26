const ctx = document.getElementById('myChart').getContext('2d');
const blogTrendChart = new Chart(ctx, {
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
                text: 'Blog Trend Chart',
                position: 'bottom',
                font: {
                    size: 16
                }
            }
        }
    }
});

const subjectCtx = document.getElementById("subjectChart");
const subjectChart = new Chart(subjectCtx, {
    type: 'doughnut',
    data: {
        labels: [
            'Red',
            'Blue',
            'Yellow',
        ],
        datasets: [{
                label: 'My First Dataset',
                data: [300, 50, 100],
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)'
                ],
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

const blogTrendCtx = document.getElementById("blogTrendChart");
const blogTrendChart2 = new Chart(blogTrendCtx, {
    type: 'radar',
    data: {
        labels: [
            'Eating',
            'Drinking',
            'Sleeping',
            'Designing',
            'Coding',
            'Cycling',
            'Running'
        ],
        datasets: [{
                label: 'My First Dataset',
                data: [65, 59, 90, 81, 56, 55, 40],
                fill: true,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgb(255, 99, 132)',
                pointBackgroundColor: 'rgb(255, 99, 132)',
                pointBorderColor: '#fff',
                pointHoverBackgroundColor: '#fff',
                pointHoverBorderColor: 'rgb(255, 99, 132)'
            }, {
                label: 'My Second Dataset',
                data: [28, 48, 40, 19, 96, 27, 100],
                fill: true,
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgb(54, 162, 235)',
                pointBackgroundColor: 'rgb(54, 162, 235)',
                pointBorderColor: '#fff',
                pointHoverBackgroundColor: '#fff',
                pointHoverBorderColor: 'rgb(54, 162, 235)'
            }]
    }
});

const revenueCtx = document.getElementById('revenueChart').getContext('2d');
const revenueChart = new Chart(revenueCtx, {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
                data: [],
                label: "",
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
//        scales: {
//            y: {
//                beginAtZero: true
//            }
//        }
        plugins: {
            legend: {
                display: false
            }
        }
    }
});

function drawCourseSubjectChart() {
    fetch("http://localhost:8080/OnlineLearning/api/statist/coursesubject")
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
    fetch("../api/statist/enrollcourse")
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

function drawRevenueChart() {
    fetch("../api/statist/revenue")
            .then(resp => resp.json())
            .then(data => {
                let monthInYear = data.map(e => e.monthInYear);
                let revenues = data.map(e => e.revenue);
                revenueChart.data.labels = monthInYear;
                revenueChart.data.datasets[0].data = revenues;
                revenueChart.update();
            })
            .catch(error => console.log(error));
}

function drawBlogTrendChart() {
    fetch("../api/statist/blogcategorytrend")
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
drawBlogTrendChart();
drawCourseSubjectChart();
drawCourseEnrollTable();
drawRevenueChart();