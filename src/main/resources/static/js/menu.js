var currentPage = window.location.pathname;
var queryString =  (window.location.search);

//alert ("href^='" + currentPage + queryString + "'");

//$('#main-menu li').on('click', 'a', function (e) {
//    
//    // only do the following if the clicked link isn't already active
//    if(!$(this).closest('li').hasClass('active')) {
//            $(this).closest('ul').find('.active').removeClass('active');
//            $(this).closest('li').addClass('active');
//
//        // load in your content via ajax, etc.
//    }
//    e.preventDefault();
//    e.stopPropagation();
//});

$('#main-menu-list').find('a[href^="' + currentPage + queryString + '"]').closest('ul').find('.active').removeClass('active');
$('#main-menu-list').find('a[href^="' + currentPage + queryString + '"]').closest('li').addClass('active');