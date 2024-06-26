/*  ---------------------------------------------------
	Template Name: Sona
	Description: Sona Hotel Html Template
	Author: Colorlib
	Author URI: https://colorlib.com
	Version: 1.0
	Created: Colorlib
---------------------------------------------------------  */

'use strict';

(function($) {
	/*------------------
		Preloader
	--------------------*/
	$(window).on('load', function() {
		$(".loader").fadeOut();
		$("#preloder").delay(200).fadeOut("slow");
	});

	/*------------------
		Background Set
	--------------------*/
	$('.set-bg').each(function() {
		var bg = $(this).data('setbg');
		$(this).css('background-image', 'url(' + bg + ')');
	});

	// Offcanvas Menu
	$(".canvas-open").on('click', function() {
		$(".offcanvas-menu-wrapper").addClass("show-offcanvas-menu-wrapper");
		$(".offcanvas-menu-overlay").addClass("active");
	});

	$(".canvas-close, .offcanvas-menu-overlay").on(
			'click',
			function() {
				$(".offcanvas-menu-wrapper").removeClass(
						"show-offcanvas-menu-wrapper");
				$(".offcanvas-menu-overlay").removeClass("active");
			});
	
	$(document).ready(function() {
	    // Kiểm tra nếu URL hiện tại là trang home
	    if (window.location.pathname === "/DatPhongKhachSanWeb/home") {
	        // Xóa sessionStorage
	        sessionStorage.setItem('activeTab', 0);
	    }

	    // Khôi phục trạng thái của các thẻ li từ sessionStorage
	    var activeTab = sessionStorage.getItem('activeTab');
	    if (activeTab !== null) {
	        $('.menu-item .nav-menu .mainmenu li').removeClass('active');
	        $('.menu-item .nav-menu .mainmenu li').eq(activeTab).addClass('active');

	        if ($('.menu-item .nav-menu .mainmenu li').eq(activeTab).find('a').text().trim() !== 'Home') {
	            // Thêm hoặc xóa lớp 'header-normal' từ phần header tùy thuộc vào việc có sẵn lớp này hay không
	            $('.header-section').toggleClass('header-normal');
	        }
	    }

	    // Thiết lập sự kiện click cho các mục menu
	    $('.menu-item .nav-menu .mainmenu li').click(function() {
	        // Xóa lớp active khỏi tất cả các thẻ li trong menu
	        $('.menu-item .nav-menu .mainmenu li').removeClass('active');
	        // Thêm lớp active vào thẻ li được click
	        $(this).addClass('active');

	        // Lưu trạng thái của các thẻ li vào sessionStorage sau khi xử lý click
	        sessionStorage.setItem('activeTab', $(this).index());
	    });
	});

//	$('.bt-option a').click(function() {
//		sessionStorage.setItem('activeTab', 0);
//	})
//
//	$('.logo').click(function() {
//		sessionStorage.setItem('activeTab', 0);
//	})

	

	// Search model
	$('.search-switch').on('click', function() {
		$('.search-model').fadeIn(400);
	});

	$('.search-close-switch').on('click', function() {
		$('.search-model').fadeOut(400, function() {
			$('#search-input').val('');
		});
	});

	/*------------------
		Navigation
	--------------------*/
	$(".mobile-menu").slicknav({
		prependTo : '#mobile-menu-wrap',
		allowParentLinks : true
	});

	/*------------------
		Hero Slider
	--------------------*/
	$(".hero-slider").owlCarousel({
		loop : true,
		margin : 0,
		items : 1,
		dots : true,
		animateOut : 'fadeOut',
		animateIn : 'fadeIn',
		smartSpeed : 1200,
		autoHeight : false,
		autoplay : true,
		mouseDrag : false
	});

	/*------------------------
		Testimonial Slider
	----------------------- */
	$(".testimonial-slider").owlCarousel(
			{
				items : 1,
				dots : false,
				autoplay : true,
				loop : true,
				smartSpeed : 1200,
				nav : true,
				navText : [ "<i class='arrow_left'></i>",
						"<i class='arrow_right'></i>" ]
			});

	/*------------------
		Magnific Popup
	--------------------*/
	$('.video-popup').magnificPopup({
		type : 'iframe'
	});

	/*------------------
		Date Picker
	--------------------*/
	/*
	 * $(".date-input").datepicker({ minDate: 1, dateFormat: 'dd MM, yy'
	 * }).attr("readonly", "readonly").on("focus", function() { $(this).blur();
	 * });
	 */
	$(".date-input").datepicker({
		minDate : 1,
		dateFormat : 'dd MM, yy',
		onSelect : function() {
			var dateIn = $("#date-in").datepicker("getDate");
			var dateOut = $("#date-out").datepicker("getDate");
			// Kiểm tra nếu ngày ra nhỏ hơn hoặc bằng ngày đến, cập nhật ngày ra
			// thành ngày đến + 1 ngày
			if (dateOut <= dateIn && dateOut != null) {
				if (this.id === "date-out") {
					dateOut.setDate(dateIn.getDate() + 1);
					$(this).datepicker("setDate", dateOut);
				} else {
					dateIn.setDate(dateOut.getDate() - 1);
					$(this).datepicker("setDate", dateIn);
				}
			}
		}
	}).attr("readonly", "readonly").on("focus", function() {
		$(this).blur();
	});
	/*------------------
		Nice Select
	--------------------*/
	$("select").niceSelect();

})(jQuery);