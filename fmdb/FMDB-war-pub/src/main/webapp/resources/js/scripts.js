function toggleGov() {
    var menu = document.getElementById('government-menu');
    if (menu) {
        menu.style.display = (menu.style.display === 'block') ? 'none' : 'block';
    }
}

document.addEventListener('click', function(event) {
    if (event.target.closest('.navbar-government-header-button') ||
        event.target.closest('.government-menu-close-button')) {
        toggleGov();
    }
});

if (window.$ && window.PrimeFaces) {
    $(document).on('pfAjaxStart', function() {
        if (PF('statusDialog')) {
            PF('statusDialog').show();
        }
    });
    $(document).on('pfAjaxComplete', function() {
        if (PF('statusDialog')) {
            PF('statusDialog').hide();
        }
    });
}