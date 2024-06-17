// banner_swiper
let banner_swiper = new Swiper(".banner-container", {
  loop: true,
  autoplay: {
    delay: 2000,
    disableOnInteraction: false,
  },
  slidesPerView: 1,
});

// pick_swiper
let pick_swiper = new Swiper(".pick-container", {
    loop: true,
    autoplay: {
      delay: 2000,
      disableOnInteraction: false,
    },
    slidesPerView: 3,
    // spaceBetween: 10,
    navigation: {
      nextEl: ".pick-ex .swiper-button-next",
      prevEl: ".pick-ex .swiper-button-prev",
    },
  });

  // expert_swiper
  let expert_swiper = new Swiper(".expert-container", {
    loop: true,
    autoplay: {
      delay: 2000,
      disableOnInteraction: false,
    },
    slidesPerView: 4,
    navigation: {
      nextEl: ".expert-ex .swiper-button-next",
      prevEl: ".expert-ex .swiper-button-prev",
    },
  });

    // center_swiper
    let center_swiper = new Swiper(".center-container", {
      loop: true,
      autoplay: {
        delay: 2000,
        disableOnInteraction: false,
      },
      slidesPerView: 4,
      navigation: {
        nextEl: ".center-ex .swiper-button-next",
        prevEl: ".center-ex .swiper-button-prev",
      },
    });