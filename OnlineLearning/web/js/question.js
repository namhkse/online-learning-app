let availableQuestions = [];
let questionCounter = 0;
let userAnswers = [];
let markedQuestions = [];
let remainSecond = 0;
let endTime = 0;

const btnNextQuestion = document.getElementById("btn_next");
const btnPreviousQuestion = document.getElementById("btn_previous");
const btnScoreExam = document.getElementById("btn_score");
const bntReviewProgress = document.getElementById("btn_review_question");
const questionTextDescription = document.getElementById("question_text");
const btnMark = document.getElementById("btn_mark");
const countDown = document.getElementById("countDown");

function removeItemInArray(array, item) {
    let index = array.indexOf(item);
    if (index > -1) {
        array.splice(index, 1);
    }
}

btnMark.addEventListener('click', () => {
    markedQuestions = JSON.parse(localStorage.getItem("markedQuestions")) || [];
    if (isMarkedQuestion(questionCounter)) {
        btnMark.innerHTML = "Mark For Review";
        removeItemInArray(markedQuestions, questionCounter);
    } else {
        btnMark.innerHTML = "Unmark";
        markedQuestions.push(questionCounter);
    }
    localStorage.setItem("markedQuestions", JSON.stringify(markedQuestions));
});

function isMarkedQuestion(number) {
    return markedQuestions.includes(number);
}

let questionApi = "http://localhost:8080/OnlineLearning/api/question?lesson=6";

class Answer {
    constructor(questionId) {
        this.questionId = questionId;
        this.answerIds = new Array();
    }
}


function initQuestion() {
    let url = new URL(window.location.href);
    let lessonId = url.searchParams.get("id");

    if (!lessonId) {
        throw new Error("Lack param id in query string");
    }

    fetch(`./api/question?lesson=${lessonId}`)
            .then(resp => resp.json())
            .then(data => {
                console.log("Loaded Question");
                availableQuestions = data;
                questionCounter = 0;
                userAnswers = JSON.parse(localStorage.getItem("userAnswers")) || [];
                markedQuestions = JSON.parse(localStorage.getItem("markedQuestions")) || [];
                loadQuestion(questionCounter, availableQuestions);
            });

    fetch(`http://localhost:8080/OnlineLearning/api/question/remaintime/${lessonId}`)
            .then(resp => resp.json())
            .then(data => {
                endTime = new Date(data.finish);
                let x = setInterval(() => {
                    let distance = endTime - new Date();

                    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                    countDown.innerHTML = `${hours}:${minutes}:${seconds}`;
                    if (distance < 0) {
                        clearInterval(x);
                        countDown.innerHTML = "EXPIRED";
                    }
                }, 1000);
            });
}


initQuestion();


function saveAnswer(questionId, answerId) {
    let answer = userAnswers.find(s => s.questionId == questionId);
    if (answer) {

    } else {

    }

}

function clearQuestionOption() {
    let options = document.querySelectorAll(".question-answer");
    options.forEach(o => o.remove());
}

function scanSelectedAnswer() {
    let questionId = availableQuestions[questionCounter].id;
    let selectedAnswerIds = getSelectedAnswerIds();
    let solved = userAnswers.find(s => s.questionId == questionId);

    if (solved) {
        solved.answerIds = selectedAnswerIds;
        console.log(`Update ${questionId} with ${selectedAnswerIds}`);
    } else {
        console.log(`Create ${questionId} with ${selectedAnswerIds}`);
        userAnswers.push(new UserAnswer(questionId, ...selectedAnswerIds));
    }

    localStorage.setItem("userAnswers", JSON.stringify(userAnswers));
}


function getSelectedAnswerIds() {
    let ids = [];
    let answers = document.getElementsByClassName("answer");
    for (let a of Array.from(answers)) {
        if (a.checked) {
            ids.push(a.value);
        }
    }
    return ids;
}

function loadSelectedAnswer(questionId) {
    let answer = userAnswers.find(q => q.questionId == questionId);

    if (answer && answer.answerIds) {
        for (let i of answer.answerIds) {
            try {
                document.getElementById(`answer-${i}`).checked = "true";
            } catch (error) {
                console.log(error);
            }
        }
    }
}

function loadQuestion(questionNo, questions) {
    let currentQuestion = questions[questionNo];

    btnMark.innerHTML = isMarkedQuestion(questionNo) ? "Unmark" : "Mark For Review";

    if (questionNo == 0) {
        btnPreviousQuestion.style.display = "none";
        btnNextQuestion.style.display = "inline";
        btnScoreExam.style.display = "none";
    } else if (questionNo == questions.length - 1) {
        btnPreviousQuestion.style.display = "inline";
        btnNextQuestion.style.display = "none";
        btnScoreExam.style.display = "inline";
    } else {
        btnPreviousQuestion.style.display = "inline";
        btnNextQuestion.style.display = "inline";
        btnScoreExam.style.display = "none";
    }

    questionTextDescription.innerText = currentQuestion.text;
    clearQuestionOption();

    document.getElementById("number_question").innerText = `${questionNo + 1}/${questions.length}`;
    document.getElementById("current_question_no").innerText = `${questionNo + 1})`;
    document.getElementById("current_question_id").innerText = `QuestionID: ${currentQuestion.id}`;

    let questionContent = document.getElementById("question_content");
    for (let answer of currentQuestion.answers) {
        let optionNode = `
            <div class="question-answer pb-2">
                <span class="wraper-radio">
                    <input id="answer-${answer.id}" class="answer" value="${answer.id}" type="checkbox" name="question-1">
                </span>
                <label for="answer-${answer.id}">${answer.text}</label>
            </div>`;
        questionContent.innerHTML += optionNode;
    }

    loadSelectedAnswer(currentQuestion.id);
}

document.getElementById("question_content").addEventListener("click", event => {
    let answer = event.target;
    if (answer.nodeName == "INPUT") {
        let questionId = availableQuestions[questionCounter].id;
        let savedAnswer = userAnswers.find(s => s.questionId == questionId);
        let selectedIds = getSelectedAnswerIds();
        console.log(selectedIds);
        if (savedAnswer) {
            console.log("Update");
            savedAnswer.answerIds = selectedIds;
        } else {
            console.log("Create");
            let temp = new Answer(questionId);
            temp.answerIds = selectedIds;
            userAnswers.push(temp);
        }
        localStorage.setItem("userAnswers", JSON.stringify(userAnswers));
    }
});

btnNextQuestion.addEventListener('click', () => {
    let next = questionCounter + 1;
    if (next < availableQuestions.length) {
        questionCounter = next;
        loadQuestion(next, availableQuestions);
    }
});

btnPreviousQuestion.addEventListener('click', () => {
    let prev = questionCounter - 1;
    if (prev >= 0) {
        questionCounter = prev;
        loadQuestion(prev, availableQuestions);
    }
});

bntReviewProgress.addEventListener('click', function () {
    loadQuestionBox(availableQuestions);
});

function loadQuestionBox(questions) {
    let questionReview = document.getElementById("question_review");
    questionReview.innerHTML = "";

    questions.forEach((_, index) => {
        let btn = document.createElement("button");
        if (isMarkedQuestion(index)) {
            btn.className = "small-question-box marked-question-box";
            btn.innerHTML = `<i class="fa-solid fa-bookmark" ></i >${index + 1}`;
        } else {
            btn.innerHTML = index + 1;
            btn.className = "small-question-box";
        }

        btn.addEventListener('click', () => {
            $("#review-process-modal").modal("hide");
            questionCounter = index;
            loadQuestion(index, questions);
        });
        questionReview.appendChild(btn);
    });
}

function submitAnswer() {
    let json = localStorage.getItem("userAnswers");
    if (json) {
        $.ajax({
            url: `http://localhost:8080/OnlineLearning/api/question/submit`,
            type: 'POST',
            data: json,
            contentType: 'application/json',
            success: function (data, textStatus, jqXHR) {
                console.log("Submit OK");
            },
            error: function () {
                console.log('Submit Failed');
            }
        });
    }
}

