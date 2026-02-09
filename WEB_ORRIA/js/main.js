document.addEventListener('DOMContentLoaded', () => {
    const track = document.querySelector('.carousel-track');
    const slides = Array.from(track.children);
    const nextButton = document.querySelector('.next-btn');
    const prevButton = document.querySelector('.prev-btn');

    // Position slides
    const moveToSlide = (track, currentSlide, targetSlide) => {
        const targetIndex = slides.findIndex(slide => slide === targetSlide);
        track.style.transform = `translateX(-${targetIndex * 100}%)`;
        currentSlide.classList.remove('current-slide');
        targetSlide.classList.add('current-slide');
    }

    // Set first slide as current
    slides[0].classList.add('current-slide');

    // Button listeners
    nextButton.addEventListener('click', e => {
        const currentSlide = track.querySelector('.current-slide');
        let nextSlide = currentSlide.nextElementSibling;
        if (!nextSlide) nextSlide = slides[0];

        moveToSlide(track, currentSlide, nextSlide);
    });

    prevButton.addEventListener('click', e => {
        const currentSlide = track.querySelector('.current-slide');
        let prevSlide = currentSlide.previousElementSibling;
        if (!prevSlide) prevSlide = slides[slides.length - 1];

        moveToSlide(track, currentSlide, prevSlide);
    });

    // Auto play
    setInterval(() => {
        const currentSlide = track.querySelector('.current-slide');
        let nextSlide = currentSlide.nextElementSibling;
        if (!nextSlide) nextSlide = slides[0];

        moveToSlide(track, currentSlide, nextSlide);
    }, 5000);
});
