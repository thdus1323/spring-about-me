$(document).ready(function () {
    $('#addCareer').on('click', function () {
        $('#career-form').append(`
            <div class="input-group">
                <input type="text" class="form-control" name="year" placeholder="연도를 입력하세요">
                <input type="text" class="form-control" name="detail" placeholder="직무, 자격증 등을 입력하세요">
                <div class="input-group-append">
                    <button type="button" class="btn btn-outline-danger remove"><i class="fas fa-minus"></i></button>
                </div>
            </div>
            <hr>
        `);
    });

    $('#addEducation').on('click', function () {
        $('#education-form').append(`
            <div class="input-group">
                <input type="text" class="form-control" name="school" placeholder="학교를 입력하세요">
                <input type="text" class="form-control" name="degree" placeholder="학위, 전공 등을 입력하세요">
                <div class="input-group-append">
                    <button type="button" class="btn btn-outline-danger remove"><i class="fas fa-minus"></i></button>
                </div>
            </div>
            <hr>
        `);
    });

    // 동적 폼 항목 삭제 기능
    $(document).on('click', '.remove', function () {
        $(this).closest('.input-group').next('hr').remove();
        $(this).closest('.input-group').remove();
    });
});