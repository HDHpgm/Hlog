$(document).ready(function () {
    console.log("게시물 불러오기 실행");
    findAllPost();
})

function findAllPost() {

    $.ajax({
        type: "GET",
        url: "/post/find/all",
        success: function (response) {
            console.log(response);
            addPostHtml(response);
        }
    });
}
function addPostHtml(posts) {
    for (let i = 0; i < posts.length; i++) {
        var tempHtml = `
                    <!-- Post preview-->
                    <div class="post-preview">
                        <a href="/post/detail/${posts[i].id}">
                            <h2 class="post-title">${posts[i].title}</h2>
                            <h3 class="post-subtitle">${posts[i].tags}</h3>
                        </a>
                        <p class="post-meta">
                            Posted by
                            ${posts[i].user.username}
                            on ${posts[i].createDate}
                        </p>
                    </div>
                    <!-- Divider-->
                    <hr class="my-4"/>`;
        $('#post-preview-div').append(tempHtml);
    }

}
