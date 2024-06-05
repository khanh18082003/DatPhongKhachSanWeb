const logo = document.querySelector('.menu .logo a');

logo.addEventListener('click', function(e) {
	sessionStorage.removeItem('activeTab');
})