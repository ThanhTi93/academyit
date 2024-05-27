var icon = document.querySelector(".icon");
var i = document.querySelectorAll(".icon i");
var p = document.querySelectorAll(".content p");
var h1 = document.querySelectorAll(".left h1");
var content = document.querySelectorAll(".content");
var h2 = document.getElementsByTagName("h2")[0];
icon.addEventListener("click", function () {
   p.forEach(element =>  element.classList.toggle("hidden"));
   h1.forEach(element => element.classList.toggle("hidden"));
   i.forEach(element => element.classList.toggle("hidden"));
   h2.classList.toggle("hidden");
});

content.forEach((element,index) => element.addEventListener("click", () => {
    content.forEach(element => element.style.color = "white");
    content[index].style.color = "red";
}))