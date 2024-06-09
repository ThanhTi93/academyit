let slideIndexes = {
  tivi: 1,
  watch: 1,
  mobile: 1,
  tablet: 1,
  detail: 1
};

function showSlides(type, n) {
  const products = document.querySelectorAll(`.${type} .col`);
  let numProductsToShow;

  // Determine the number of products to show based on screen width
  if (window.matchMedia("(max-width: 767px)").matches) { // Mobile
    numProductsToShow = 2;
  } else if (window.matchMedia("(max-width: 1024px)").matches) { // Tablet
    numProductsToShow = 3;
  } else { // Laptop and larger devices
    numProductsToShow = 5;
  }

  // Adjust slideIndex if out of range
  if (n > products.length - numProductsToShow + 1) {
    slideIndexes[type] = 1;
  } else if (n < 1) {
    slideIndexes[type] = products.length - numProductsToShow + 1;
  }

  // Hide all products
  for (let i = 0; i < products.length; i++) {
    products[i].style.display = "none";
  }

  // Show the appropriate number of products
  for (let i = 0; i < numProductsToShow; i++) {
    if (products[slideIndexes[type] + i - 1]) {
      products[slideIndexes[type] + i - 1].style.display = "block";
    }
  }
}

function plusSlides(type, n) {
  showSlides(type, slideIndexes[type] += n);
}

// Initialize the slides
showSlides('tablet', slideIndexes.tablet);
showSlides('tivi', slideIndexes.tivi);
showSlides('watch', slideIndexes.watch);
showSlides('mobile', slideIndexes.mobile);
showSlides('detail', slideIndexes.detail);
// Automatically change slides every 2 seconds
setInterval(() => {
  showSlides('tivi', slideIndexes.tivi += 1);
  showSlides('watch', slideIndexes.watch += 1);
  showSlides('mobile', slideIndexes.mobile += 1);
  showSlides('detail', slideIndexes.detail += 1);
  showSlides('tablet', slideIndexes.tablet += 1);
}, 3000);

// Ensure slides adjust properly on window resize
window.addEventListener("resize", () => {
  showSlides('tivi', slideIndexes.tivi);
  showSlides('watch', slideIndexes.laptop);
  showSlides('mobile', slideIndexes.mobile);
  showSlides('tablet', slideIndexes.tablet);
  showSlides('detail', slideIndexes.detail);
});
