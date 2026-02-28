// 한동성 강사님 주소 입력, 위도 경도로 변경하는 라이브러리 적용

// let mapContainer = document.getElementById("map"), // 지도를 표시할 div
//     mapOption = {
//         center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
//         level: 3, // 지도의 확대 레벨
//     };

// // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
// let map = new kakao.maps.Map(mapContainer, mapOption);

let geocoder = new kakao.maps.services.Geocoder();
geocoder.addressSearch("서울특별시 용산구 새창로 181", (result, status) => {
    if (status === kakao.maps.services.Status.OK) {
        let coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        let mapContainer = document.getElementById("map"), // 지도를 표시할 div
            mapOption = {
                center: coords,
                level: 3, // 지도의 확대 레벨
            };

        // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        let map = new kakao.maps.Map(mapContainer, mapOption);

        // 결과값으로 받은 위치를 마커로 표시합니다
        let marker = new kakao.maps.Marker({
            map: map,
            position: coords,
        });

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    }
});

// const checkbox = document.getElementById("chk");
// console.log(checkbox.checked); // true / false

// const checkbox = document.getElementById("chk");
// const addressInput = document.getElementById("addressInput");

// const defaultAddress = "서울특별시 노원구 동일로 241길";

// checkbox.addEventListener("change", () => {
//   if (checkbox.checked) {
//     // 새로운 주소 사용
//     addressInput.readOnly = false;
//     addressInput.value = "";
//     addressInput.focus();
//   } else {
//     // 기존 주소 사용
//     addressInput.readOnly = true;
//     addressInput.value = defaultAddress;
//   }
// });
