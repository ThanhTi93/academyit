document.addEventListener("DOMContentLoaded", function() {
    var icon = document.querySelector(".icon");
    var i = document.querySelectorAll(".icon i");
    var p = document.querySelectorAll(".content p");
    var h1 = document.querySelectorAll(".left h1");
    var content = document.querySelectorAll(".content");
    var h2 = document.getElementsByTagName("h2")[0];

    // Add click event to icon
    icon.addEventListener("click", function () {
        p.forEach(element => element.classList.toggle("hidden"));
        h1.forEach(element => element.classList.toggle("hidden"));
        i.forEach(element => element.classList.toggle("hidden"));
        h2.classList.toggle("hidden");
    });

    // Function to highlight the selected content
    function highlightContent(selectedIndex) {
        content.forEach((element, index) => {
            if(index === selectedIndex) {
                element.style.color = "red";
            } else {
                element.style.color = "white";
            }
        });
    }

    // Add click event to each content element
    content.forEach((element, index) => {
        element.addEventListener("click", () => {
            localStorage.setItem("selectedContentIndex", index);
            highlightContent(index);
        });
    });

    // On page load, check localStorage for the selected index and highlight the content
    var selectedIndex = localStorage.getItem("selectedContentIndex");
    if (selectedIndex !== null) {
        highlightContent(parseInt(selectedIndex));
    }
});


 document.addEventListener('DOMContentLoaded', function () {
        var categorySelect = document.getElementById('category');
        var defaultOption = categorySelect.querySelector('option[value=""]');
        if(categorySelect.value == "") {
                defaultOption.disabled = true;
                defaultOption.selected = true;
        } else {
                defaultOption.disabled = true;
        }
    });

function previewImage(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById('preview-image').src = e.target.result;
            }
            reader.readAsDataURL(input.files[0]);
        }
    }