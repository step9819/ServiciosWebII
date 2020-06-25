<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="esS">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<link href="css/bootstrapValidator.min.css" rel="stylesheet">

<link href="css/bootstrap-datepicker.css" rel="stylesheet">

<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrapValidator.min.css" rel="stylesheet">


<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>

<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>


<script type="text/javascript" src="sweetalert/sweetalert.min.js"></script>




<title>Televisores</title>
<style>
	.modal-header, h4, .close {
		background-color: #286090;
		color: white !important;
		text-align: center;
		font-size: 20px;
	}
	.btn-warning,.btn-info{
		width: 100px;
	}
	.btn-danger,.btn-primary{
		width: 160px;
	}
</style> 
</head>
<body>
	<div class="container">
		<h3 align="center">Lista Televisores</h3>
			Stock 1:<input type="text"  name="cantidad1" id="idStock1"/>
			Stock 2:<input type="text"  name="cantidad2" id="idStock2"/>
			<button type="button" class="btn btn-primary">CONSULTAR</button>
			
			<div>
		 								<table id="id_table" class="table table-striped table-bordered" >
												<thead>
														<tr>
															<th>CODIGO</th>
															<th>NOMBRE</th>
															<th>PRECIO</th>
															<th>STOCK</th>
															<th>FECHA</th>
														</tr>
												</thead>
												<tbody>

												</tbody>
											</table>	

			</div>	
	</div>

<script type="text/javascript">
	$(".btn-primary").click(function(){
		
		$("#id_table tbody").empty(); 
		$.getJSON("listaTelevisoresXStock",{stock1:$("#idStock1").val(),
			stock2:$("#idStock2").val()}, function (response){
			$.each(response.listaTelevisores, function(index, item){		    
				$("#id_table").append("<tr><td>"+item.codigo+"</td><td>"+item.nombre+"</td><td>"+
						item.precio+"</td><td>"+item.stock+"</td><td>"+item.fecha+"</td></tr>");
			});
		});
	})

</script>


</body>
</html>












