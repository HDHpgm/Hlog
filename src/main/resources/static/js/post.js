// $(document).ready(function () {
//     console.log("게시물 "+ window.location.search + " 불러오기 실행");
//     $.ajax({
//         type: "GET",
//         url: "/post/find" + window.location.search,
//         success: function (response) {
//             console.log(response);
//             addHeaderHtml(response);
//             addContentHtml(response);
//         }
//     })
// })

function addHeaderHtml(post) {
    var tempHtml = `<div class="container position-relative px-4 px-lg-5">
                        <div class="row gx-4 gx-lg-5 justify-content-center">
                            <div class="col-md-10 col-lg-8 col-xl-7">
                                <div class="post-heading">
                                    <h1 id="title">${post.title}</h1>
                                    <h2 id="tags" class="subheading"># ${post.tags}</h2>
                                    <span class="meta">
                                        Posted by
                                        ${post.user.username}
                                        ${post.createDate}
                                    </span>
                                </div>

                            </div>
                        </div>

                    </div>`;
    $('#page-header').prepend(tempHtml);
}

function addContentHtml(post) {
    var tempHtml = post.contentHtml;
    $('#page-content').append(tempHtml);
}

function deletePost() {
    var deleteReturn = confirm('정말 삭제하시겠습니까?');
    var postId = window.location.pathname[window.location.pathname.length - 1];

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