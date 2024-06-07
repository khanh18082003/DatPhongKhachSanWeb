window.addEventListener('DOMContentLoaded', event => {
	// Simple-DataTables
	// https://github.com/fiduswriter/Simple-DataTables/wiki

	const datatablesSimple = document.getElementById('datatablesSimple');
	if (datatablesSimple) {
		new simpleDatatables.DataTable(datatablesSimple);
	}

	$(document).ready(function() {
		var table = $('#datatablesSimple').DataTable();

		function updateTotal() {
			var total = 0;
			table.rows({ search: 'applied' }).every(function() {
				var data = this.data();
				var lineTotal = parseFloat(data[3].replace(/[$,]/g, ''));
				total += lineTotal;
			});
			$('#total').html('$' + total.toFixed(2));
		}

		table.on('draw', function() {
			updateTotal();
		});

		updateTotal();
	});
});

