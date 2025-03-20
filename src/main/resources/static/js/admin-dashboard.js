// DOM Elements
const modal = document.getElementById('courseModal');
const addCourseBtn = document.querySelector('.add-course-btn');
const closeBtn = document.querySelector('.close');
const cancelBtn = document.querySelector('.cancel-btn');
const courseForm = document.querySelector('.course-form');
const coursesTable = document.querySelector('.courses-table tbody');

// Sample courses data (replace with actual data from your backend)
let courses = [
    {
        id: 1,
        title: 'Web Design & Development',
        category: 'Information Technologies',
        status: 'active',
        description: 'Learn web development from scratch'
    },
    {
        id: 2,
        title: 'Digital Marketing Fundamentals',
        category: 'Digital Marketing',
        status: 'draft',
        description: 'Master digital marketing strategies'
    },
    {
        id: 3,
        title: 'Business Analytics',
        category: 'Business & Entrepreneurship',
        status: 'active',
        description: 'Understand business analytics'
    }
];

// Modal Functions
function openAddCourseModal() {
    modal.style.display = 'block';
    courseForm.reset();
    courseForm.setAttribute('data-mode', 'add');
}

function closeModal() {
    modal.style.display = 'none';
}

// Event Listeners
addCourseBtn.addEventListener('click', openAddCourseModal);
closeBtn.addEventListener('click', closeModal);
cancelBtn.addEventListener('click', closeModal);

window.addEventListener('click', (event) => {
    if (event.target === modal) {
        closeModal();
    }
});

// Course CRUD Operations
function addCourse(courseData) {
    const newId = courses.length + 1;
    const newCourse = {
        id: newId,
        ...courseData,
        status: 'draft'
    };
    courses.push(newCourse);
    renderCourseRow(newCourse);
}

function updateCourse(id, courseData) {
    const index = courses.findIndex(course => course.id === id);
    if (index !== -1) {
        courses[index] = { ...courses[index], ...courseData };
        refreshCoursesTable();
    }
}

function deleteCourse(id) {
    if (confirm('Are you sure you want to delete this course?')) {
        courses = courses.filter(course => course.id !== id);
        refreshCoursesTable();
    }
}

// Render Functions
function renderCourseRow(course) {
    const row = document.createElement('tr');
    row.innerHTML = `
        <td>${course.id}</td>
        <td>${course.title}</td>
        <td>${course.category}</td>
        <td><span class="status ${course.status}">${course.status}</span></td>
        <td class="actions">
            <button class="edit-btn" onclick="editCourse(${course.id})">
                <i class="fa fa-edit"></i>
            </button>
            <button class="delete-btn" onclick="deleteCourse(${course.id})">
                <i class="fa fa-trash"></i>
            </button>
            <button class="view-btn" onclick="viewCourse(${course.id})">
                <i class="fa fa-eye"></i>
            </button>
        </td>
    `;
    coursesTable.appendChild(row);
}

function refreshCoursesTable() {
    coursesTable.innerHTML = '';
    courses.forEach(course => renderCourseRow(course));
}

function editCourse(id) {
    const course = courses.find(c => c.id === id);
    if (course) {
        courseForm.elements.courseTitle.value = course.title;
        courseForm.elements.courseCategory.value = course.category.toLowerCase().replace(/ & /g, '-');
        courseForm.elements.courseDescription.value = course.description;
        courseForm.setAttribute('data-mode', 'edit');
        courseForm.setAttribute('data-course-id', id);
        openAddCourseModal();
    }
}

function viewCourse(id) {
    const course = courses.find(c => c.id === id);
    if (course) {
        // Implement view functionality (e.g., redirect to course detail page)
        alert(`Viewing course: ${course.title}`);
    }
}

// Form Submission
courseForm.addEventListener('submit', (e) => {
    e.preventDefault();
    
    const courseData = {
        title: courseForm.elements.courseTitle.value,
        category: courseForm.elements.courseCategory.value,
        description: courseForm.elements.courseDescription.value
    };

    const mode = courseForm.getAttribute('data-mode');
    
    if (mode === 'add') {
        addCourse(courseData);
    } else if (mode === 'edit') {
        const courseId = parseInt(courseForm.getAttribute('data-course-id'));
        updateCourse(courseId, courseData);
    }

    closeModal();
});

// Search Functionality
const searchInput = document.createElement('input');
searchInput.type = 'text';
searchInput.placeholder = 'Search courses...';
searchInput.classList.add('search-input');
document.querySelector('.dashboard-header').appendChild(searchInput);

searchInput.addEventListener('input', (e) => {
    const searchTerm = e.target.value.toLowerCase();
    const filteredCourses = courses.filter(course => 
        course.title.toLowerCase().includes(searchTerm) ||
        course.category.toLowerCase().includes(searchTerm)
    );
    coursesTable.innerHTML = '';
    filteredCourses.forEach(course => renderCourseRow(course));
});

// Initialize table
document.addEventListener('DOMContentLoaded', () => {
    refreshCoursesTable();
});

// API Integration (example functions - implement according to your backend)
async function fetchCourses() {
    try {
        const response = await fetch('/api/courses');
        const data = await response.json();
        courses = data;
        refreshCoursesTable();
    } catch (error) {
        console.error('Error fetching courses:', error);
    }
}

async function saveCourse(courseData) {
    try {
        const response = await fetch('/api/courses', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(courseData)
        });
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error saving course:', error);
    }
}

// Error Handling
function showError(message) {
    const errorDiv = document.createElement('div');
    errorDiv.classList.add('error-message');
    errorDiv.textContent = message;
    document.querySelector('.admin-container').prepend(errorDiv);
    setTimeout(() => errorDiv.remove(), 3000);
}

// Add this to your CSS file
const styles = `
    .search-input {
        padding: 0.5rem 1rem;
        border: 1px solid #ddd;
        border-radius: 4px;
        margin-left: auto;
        margin-right: 1rem;
    }

    .error-message {
        background-color: #f44336;
        color: white;
        padding: 1rem;
        margin-bottom: 1rem;
        border-radius: 4px;
        text-align: center;
    }
`;

// Create and append style element
const styleSheet = document.createElement('style');
styleSheet.textContent = styles;
document.head.appendChild(styleSheet);