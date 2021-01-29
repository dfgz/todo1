<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<script src="js/frm_estudiante.js"></script>


<body ng-controller="facturaController" ng-init="inicio()">


	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>Codigo</th>
				<th>Nombre</th> 
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="item in registro" ng-click="getRegistro(item.lla)">
				<td>{{item.codigo}}</td>
				<td>{{item.nombre}}</td> 
			</tr>

		</tbody>
	</table>



</body>
</html>