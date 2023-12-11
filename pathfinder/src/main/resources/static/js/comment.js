const postCommentElement = document.getElementById('postComment');
postCommentElement.addEventListener('click', createComment);

function createComment(){
    const commentTextElement = document.getElementById('message');
    const textContent = commentTextElement.value
    const routeId = document.querySelector('input[name="routeId"]').value

    fetch('http://localhost:8080/api/comments/create',
    {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({routeId,textContent })
    })
    .then(res => res.json())
    .then(res => {

        const id = res.id;
        const approved = res.approved;
        const authorName = res.authorName;
        const content = res.content;

        const comment = document.createElement('div');
        comment.classList.add('form-group');

        console.log(comment);

        const contentElement = document.createElement('h4');
        const contentElementValue = document.createTextNode(content);
        contentElement.appendChild(contentElementValue);

console.log(contentElement);

        const nameElement = document.createElement('label');
        const nameElementValue = document.createTextNode(authorName);
        nameElement.appendChild(nameElementValue);

console.log(nameElement);

        comment.appendChild(contentElement);
        comment.appendChild(nameElement);

console.log(comment);

        const allCommentsElement = document.querySelector('div[class="comments"]');
        allCommentsElement.appendChild(comment);

        console.log(allCommentsElement);

        commentTextElement.value = '';
    })
}