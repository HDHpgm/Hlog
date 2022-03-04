function readTag() {
    var tagStr = $('#input-tag').val();
    $('#input-tag').val("");
    // 중복 tag 체크
    // 공백 체크
    var blank_pattern = /^\s+|\s+$/g;
    if (tagStr === null || tagStr.replace(blank_pattern, '') === "") {
        alert("태그를 입력해주세요");
    } else addTag(tagStr);
}

var tagCount = -1;
var tagPrefix = "tag-";

function addTag(tagStr) {
    // 색깔 랜덤으로
    const color = ["primary", "secondary", "success", "danger", "warning", "info", "light", "dark"];
    const randomColor = Math.floor(Math.random() * color.length);
    // id 만들어주기, 삭제위해서
    var id = tagPrefix + (++tagCount);
    var tempHtml = `<span id="${id}" style="cursor: pointer;" onclick="deleteTag('${id}')" class="badge badge-${color[randomColor]} mr-2 align-self-center">${tagStr}</span>`;

    // html 붙이기
    $('#output-tag').append(tempHtml);

}

// 클릭한 tag 삭제
function deleteTag(id) {
    $('#' + id).remove();
}

$(document).ready(function () {
    $('#write-btn').on("click", function () {
        writePost();
    });

    $('#edit-btn').on("click", function () {
        editPost();
    });
})

function writePost() {
    var contentHtml = $('#out').html();

    // 모든 태그 가져오기
    var tagArr = [];
    for (var i = 0; i <= tagCount; i++) {
        var tag = $('#' + tagPrefix + i).text();

        if (tag === '') {
            continue;
        }
        tagArr[i] = tag;
    }
    console.log(tagArr);


    $.ajax({
        type: 'POST',
        url: '/post/write',
        contentType: 'application/json',
        data: JSON.stringify({
            "title": $('#post-title').val(),
            "contentHtml": contentHtml,
            "tags": tagArr
        }),
        success: function (response) {
            console.log(response);
            var success = response.success;
            if (success === "false") {
                alert(response.errorMessage);
            } else {
                alert(response);
                $('#markdown-html').remove();
                location.href = '/';
            }

        }
    });
}


function deletePost() {
    var deleteReturn = confirm('정말 삭제하시겠습니까?');
    var urlArr = window.location.pathname.split('/');
    var postId = urlArr[urlArr.length - 1];
    console.log(postId);
    console.log(urlArr);
    if (deleteReturn) {
        $.ajax({
            type: 'delete',
            url: '/post/delete/' + postId,
            success: function (response) {
                alert(response);
                window.location.href = '/';
            },
            error: function (error) {
                alert(error.statusText);
            }
        })
    }
}

// 게시물 수정 요청
function editPost() {

    var contentHtml = $('#out').html();
    var urlArr = window.location.pathname.split('/');
    var postId = urlArr[urlArr.length - 1];
    // 모든 태그 가져오기
    var tagArr = [];
    for (var i = 0; i <= tagCount; i++) {
        var tag = $('#' + tagPrefix + i).text();

        if (tag === '') {
            continue;
        }
        tagArr[i] = tag;
    }
    $.ajax({
        type: 'put',
        url: '/post/update/' + postId,
        contentType: 'application/json',
        data: JSON.stringify({
            "title": $('#post-title').val(),
            "contentHtml": contentHtml,
            "tags": tagArr
        }),
        success: function (response) {
            alert(response);
            window.location.href = '/';
        },
        error: function (error) {
            alert(error.statusText);
        }
    })
}