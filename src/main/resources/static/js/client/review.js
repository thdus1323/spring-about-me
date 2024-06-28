document.addEventListener('DOMContentLoaded', function () {
    const ratingLabels = document.querySelectorAll('.rating label');
    const reviewScoreInput = document.getElementById('reviewScore');

    ratingLabels.forEach(label => {
        label.addEventListener('mouseover', () => {
            let currentLabel = label;
            while (currentLabel) {
                currentLabel.style.color = '#ffa45b';
                currentLabel = currentLabel.previousElementSibling;
                if (currentLabel && currentLabel.tagName !== 'LABEL') {
                    break;
                }
            }
        });

        label.addEventListener('mouseout', () => {
            const checkedInput = document.querySelector('.rating input:checked');
            ratingLabels.forEach(label => {
                if (!checkedInput || label.htmlFor > checkedInput.id) {
                    label.style.color = '#ddd';
                } else {
                    label.style.color = '#ffa45b';
                }
            });
        });

        label.addEventListener('click', () => {
            const selectedRating = document.querySelector('.rating input:checked').value;
            reviewScoreInput.value = selectedRating;
        });
    });
});