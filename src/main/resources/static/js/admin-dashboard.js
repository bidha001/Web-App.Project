// Get the modal
const modal = document.getElementById("courseModal");

// Function to open the modal for adding a new course
function openAddCourseModal() {
  modal.style.display = "block";
  // Prevent body scrolling when modal is open
  document.body.style.overflow = "hidden";

  // Reset form fields and scroll position to top
  const courseForm = document.querySelector('.course-form');
  if (courseForm) {
    courseForm.reset();
  }

  setTimeout(() => {
    const modalContent = document.querySelector('.modal-content');
    if (modalContent) {
      modalContent.scrollTop = 0;
    }
  }, 10);
}

// Function to close the modal
function closeModal() {
  modal.style.display = "none";
  // Re-enable body scrolling when modal is closed
  document.body.style.overflow = "auto";

  // Optional: Reset form fields
  // document.querySelector('.course-form').reset();
}

// Close the modal when clicking on the X button
document.querySelector(".close").onclick = function () {
  closeModal();
}

// Close the modal when clicking outside of it
window.onclick = function (event) {
  if (event.target == modal) {
    closeModal();
  }
}

// Close modal when Cancel button is clicked
document.querySelector(".cancel-btn").onclick = function () {
  closeModal();
}

// Handle form submission with loading state
document.querySelector('.course-form').addEventListener('submit', function () {
  const saveButton = document.getElementById('saveCourseBtn');
  saveButton.innerHTML = '<i class="fa fa-spinner fa-spin"></i> Saving...';
  saveButton.disabled = true;
});
