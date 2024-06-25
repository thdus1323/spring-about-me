// $(document).ready(function () {
//     // 프로필 수정 버튼 클릭 시
//     $('#editProfile').on('click', function () {
//         $('#profile-view-card').addClass('d-none');
//         $('#profile-edit-card').removeClass('d-none');
//     });
//
//     // 취소 버튼 클릭 시
//     $('#cancelEdit').on('click', function () {
//         $('#profile-edit-card').addClass('d-none');
//         $('#profile-view-card').removeClass('d-none');
//     });
//
//     // 저장 버튼 클릭 시
//     $('#saveProfile').on('click', function () {
//         const title = $('#edit-title').val();
//         const profileImageSrc = $('#profileImageEdit').attr('src');
//
//         // 보기 모드에 값 반영
//         $('#title').text(title);
//         $('#profileImageView').attr('src',profileImageSrc);
//
//         // 폼 전환
//         $('#profile-edit-card').addClass('d-none');
//         $('#profile-view-card').removeClass('d-none');
//
//         // 서버로 데이터 전송
//         $.ajax({
//             url: '/expert/profiles',
//             type: 'POST',
//             contentType: 'application/json',
//             data: JSON.stringify({
//                 title: title,
//                 profileImage: profileImageSrc
//             }),
//             success: function () {
//                 alert('프로필이 저장되었습니다.');
//             },
//             error: function () {
//                 alert('저장에 실패했습니다.');
//             }
//         });
//     });
//
//     // 경력 항목 추가 버튼 클릭 시
//     $('#addCareer').on('click', function () {
//         const yearOptions = generateYearOptions();
//         $('#career-form').append(`
//             <div class="input-group">
//                 <select class="form-control" name="year">${yearOptions}</select>
//                 <input type="text" class="form-control" name="detail" placeholder="직무, 자격증 등을 입력하세요">
//                 <div class="input-group-append">
//                     <button type="button" class="btn btn-outline-danger remove"><i class="fas fa-minus"></i></button>
//                 </div>
//             </div>
//             <hr>
//         `);
//     });
//
//     // 학력 항목 추가 버튼 클릭 시
//     $('#addEducation').on('click', function () {
//         const yearOptions = generateYearOptions();
//         $('#education-form').append(`
//             <div class="input-group">
//                 <select class="form-control" name="year">${yearOptions}</select>
//                 <input type="text" class="form-control" name="school" placeholder="학교를 입력하세요">
//                 <input type="text" class="form-control" name="degree" placeholder="학위, 전공 등을 입력하세요">
//                 <div class="input-group-append">
//                     <button type="button" class="btn btn-outline-danger remove"><i class="fas fa-minus"></i></button>
//                 </div>
//             </div>
//             <hr>
//         `);
//     });
//
//     // 동적 폼 항목 삭제 기능
//     $(document).on('click', '.remove', function () {
//         $(this).closest('.input-group').next('hr').remove();
//         $(this).closest('.input-group').remove();
//     });
//
//     // 연도 선택 옵션 생성 함수
//     function generateYearOptions() {
//         const currentYear = new Date().getFullYear();
//         let options = '';
//         for (let year = currentYear; year >= 1900; year--) {
//             options += `<option value="${year}">${year}</option>`;
//         }
//         return options;
//     }
//
//     // 출생 연도 셀렉트 박스에 옵션 추가
//     const yearOptions = generateYearOptions();
//     $('#edit-birth-year').append(yearOptions);
// })