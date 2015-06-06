﻿<!DOCTYPE html>
<html lang="pt-BR">

<html>
  
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <style type="text/css">
      html { height: 100%}
      body { height: 100%; margin: 0; padding: 0 }
      #map_canvas { 
        height: 100%;
        z-index:1;
        border-left: 1px solid #000;
  	  }
          
      .topo {
          width:100%;
          height:130px;
          margin: 0 auto;
/*          text-align: center;*/
          background: #18bc9c;
      }
	  
	  h3 {
	  	  text-align:center;
	  }
      
      .topo>img{
          float:left;
          width:180px;
          margin-left:40px;
          margin-top:7px;
      }
      
      .topo h1 {
          position:absolute;
          margin-left:252px;
          margin-top:50px;
		  color:#FFF;
      }
      
           .usuario {
          width:350px;
          float:right;
          padding: 25px 20px 20px 20px;
          color:#FFF;

      }
	  
	  .usuario>img {
              width:100px;
              height:100px;
              margin-top:15px;
              margin-left:10px;
              position:relative;
              left:33px;
              border:2px solid #000;
	  }
	  
	  .usuario h2 {
              font-size:25px;
              text-align:center;
              margin-right:65px;
              margin-top:10px;
              border:2px solid #000;
	  }
	  
	  
	  .usuario h4 {
              font-size:20px;
              margin-top:30px;
              margin-right:65px;
              border:2px solid #000;
	  }
	  
	  .lista-lembretes {
              float:left;
              width:232px;
              padding: 0px 10px 0px 10px;
              height:100%;
              background: #F9F7F7;

	  }
          
          .lista-lembretes li {
              list-style:none;
              padding-bottom:15px;
          }
          
          .lista-lembretes-opcoes {
              margin-top:43px;
          }
	  td {
              text-align:center;
              border-bottom:1px solid #000;
              padding-top:19px;
              font-size:15px;
              font-weight:bold;
	  }
          
          .lembretes {
              height:50px;
              width:200px;
              z-index:2;
              position:absolute;
              margin-left:10px;
              padding:0;
              
          }
	  
	  .buscador {
		  z-index:100;  
		  margin-left:300px;
		  margin-top:30px;
		  position:absolute;
	  }
          /* =============== Estilos do autocomplete =============== */
.ui-autocomplete { 
	background: #fff; 
	border-top: 1px solid #ccc;
	cursor: pointer; 
	font: 15px 'Open Sans',Arial;
	margin-left: 3px;
	width: 493px !important;
	position: fixed;
}

.ui-autocomplete .ui-menu-item { 
	list-style: none outside none;
	padding: 7px 0px 9px 10px;
}

.ui-autocomplete .ui-menu-item:hover { 
	background: #eee;	
}

.ui-autocomplete .ui-corner-all { 
	color: #666 !important;
	display: block;
}

.campos label { 
	background: #333; 
	border-radius: 6px 0 0 6px; 
	color: #f0f0f0; 
	cursor: pointer; 
	display: inline; 
	height: 18px; 
	float: left; 
	font: 15px 'Open Sans',Arial;
	padding: 7px 13px;
	width: 80px;
}

.campos input[type="text"] { 
	background: #FFF;
	color: #666;
	display: inline;
	float: left;
	font: 15px 'Open Sans',Arial; 
	height: 12px;
	padding: 9px;
	width: 475px; 
}

.usr{
  float:left;
  margin-right: 15px; 
}

.usr>img {
	border-radius:200px;	
	width:80px;
	height:80px;
	
}

.textos-usuario{
  float:left;
  padding-top: 15px;
}

.modal-header {
	color:#000;	
}

.modal-body {
	color:#000;	
}

#retornoImg {
	float:right;	
	margin-right:5px;
	width:270px;
	height:260px;
}

#retornoImg img {
	width:270px;
	height:260px;
	border-radius:200px;	
}
    </style>
    <title>Painel MeChame</title>
    <link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="css/bootstrap-responsive.min.css" type="text/css" rel="stylesheet">
    <link href="css/jquery-ui.min.css" type="text/css" rel="stylesheet">
    <link href="css/jquery-ui.structure.min.css" type="text/css" rel="stylesheet">
    <link href="css/jquery-ui.theme.min.css" type="text/css" rel="stylesheet">
    <link href="css/jquery-ui.min.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBM9eJHTucMUncscsoUMdOBHSYetDIFwBM&sensor=FALSE">
</script>
    <script type="text/javascript" src="js/jquery.min.js">
</script>
    <script type="text/javascript" src="js/jquery-ui.custom.min.js">
</script>
    <script type="text/javascript" src="js/bootstrap.min.js">
</script>
    <script type="text/javascript" src="js/mapas.js">
</script>
	<script type="text/javascript" src="js/jquery.form.js">
</script>
  </head>
  
  <?php

	require_once '../Mobile_Detect.php';
	
	$verifica = new Mobile_Detect;	
	
	if ( $verifica->isMobile() || $verifica->isTablet() ) {
		
		echo "<meta HTTP-EQUIV='Refresh' CONTENT='0;URL=http://mechame.com.br/mobile/'>";
			
	}

	?>
  
  <body>
    <div class="topo">
      <img src="../img/mechamelogo.png">
      <h1>ME CHAME!</h1>
      <div class="usuario">
        <div class='usr'>
          <img src="img/usuario.png" title="Usuário" alt="Usuário">
        </div>
        <div class="textos-usuario">
          <div>
            <span>Bem Vindo</span>
          </div>
          <div style="float: left; font-size: 20px; font-stretch: semi-expanded">
            <span>Pablo Henrique Vianini</span>
          </div>
          <div class="dropdown" style="float:left; margin-left:10px">
            <div class="menu">
              <a data-toggle="dropdown" href="#">
              <img src="img/setup.png" width="20px" height="20px" />
               </a>
              <ul class="dropdown-menu" style="left: -150px !important">
                <li>
                    <a href="#modalPerfil" role="button" data-toggle="modal"><i class="fa fa-bar-chart-o fa-fw"></i>Perfil</a>
                </li>
                <li class="divider"></li>
                <li>
                  <a href="#">Sair</a>
                </li>
              </ul>
            </div>
          </div>
            
            <!-- Modal do Perfil do usuário -->
            <div id="modalPerfil" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3>Perfil</h3>
  </div>
  <div class="modal-body">
	
	<form id="formularioImagem" method="post" enctype="multipart/form-data" action="upload.php">
        <label for="imagem">Foto:</label>
        <input type="file" id="imagem" name="imagem" />
	</form>
    
    <div id="retornoImg"><img src="img/usuario.png" alt="Imagem Perfil" title="Imagem Perfil"></div>
    
    <form id="formulario" method="post" action="">
      <label for="nome">Nome:</label>
         <input type="text" class="form-control" id="nome" name="nome" placeholder="Rafael Pardin"><br>
      <label for="telefone">Telefone:</label>
         <input type="text" class="form-control" id="telefone" name="telefone" placeholder="+55 (11) 95369-8467"><br>
      <label for="email">E-mail:</label>
         <input type="email" class="form-control" id="email" name="email" placeholder="rafael_sombra@gmail.com"><br>
      <label for="senha">Senha:</label>
         <input type="password" class="form-control" id="senha" name="senha" placeholder="*********"><br>
    </form>
  </div>
  <div class="modal-footer">
	<button class="btn btn-success">Salvar</button>
    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Fechar</button>
  </div>
</div>
        </div> <!--Fim textos-usuario -->
      </div> <!--Fim usuario -->
    </div> <!--Fim topo -->
    
    <div class="lista-lembretes">
      <div class="lembretes">
        <h3>Lembretes</h3>
      </div>
      <div class="buscador">
        <div class="campos">
          <label for="txtEndereco">Endereço:</label>
          <input type="text" id="txtEndereco" name="txtEndereco" placeholder="Av. Inocêncio Seráfico">
          <!--<input type="button" id="btnEndereco" name="btnEndereco" value="Mostrar no mapa">-->
        </div>
        <input type="hidden" name="txtLatitude" id="txtLatitude">
        <input type="hidden" name="txtLongitude" id="txtLongitude">
      </div>
      
        <!-- Div oculta que será exibida dentro do modal ao clicar para editar. -->
        <div id="modalEditar" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3>Editar</h3>
  </div>
  <div class="modal-body">
           
       <label for="nome">Título:</label>
         <input type="text" class="form-control" id="titulo" name="titulo" placeholder="Título do Lembrete"><br> 
       <label for="raio">Raio:</label>
         <input type="text" class="form-control" id="raio" name="raio" placeholder="200"><br> 
    </div>
  <div class="modal-footer">
	<button class="btn btn-success">Salvar</button>
    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Fechar</button>
  </div>
</div>     


        <!-- Div oculta que será exibida dentro do popover de exclusão. -->
        <div id="div-exclusao" class="hide">
           
           <p>Deseja realmente excluir o lembrete?</p>
           
           <button id="sim" class="btn btn-danger">Sim</button>
           <button id="nao" class="btn btn-success" data-dismiss="clickover">Não</button>
           
        </div>
      
      <div class="lista-lembretes-opcoes">
        <table width="232" border="0">
          <tr>
            <td width="128">Mercado perto de casa</td>
            <td width="20">
              <a href="#modalEditar" role="button" data-toggle="modal"><img src="img/editar.png" alt="Editar Lembrete" width="20" height="19" title="Editar Lembrete"/></a>
            </td>
            <td width="20">
              <a href="#" class="excluir-popover"><img src="img/excluir.png" width="20" alt="Excluir Lembrete" title="Excluir Lembrete"/></a>
              <a href="#"></a>
                        
            </td>
          </tr>
          <tr>
            <td width="128">Serviço</td>
            <td width="20">
              <a href="#"><img src="img/editar.png" alt="Editar Lembrete" width="20" height="19" title="Editar Lembrete"/></a>
            </td>
            <td width="20">
              <a href="#" data-toggle="popover" title="Confirmar Exclusão" data-content="Deseja realmente excluir?"><img src="img/excluir.png" width="20" alt="Excluir Lembrete" title="Excluir Lembrete"/></a>
            </td>
          </tr>
          <tr>
            <td width="128">Faculdade</td>
            <td width="20">
              <a href="#" data-toggle="popover" title="Confirmar Exclusão" data-content="Deseja realmente excluir?"><img src="img/editar.png" alt="Editar Lembrete" width="20" height="19" title="Editar Lembrete"/></a>
            </td>
            <td width="20">
              <a href="#" data-toggle="popover" title="Confirmar Exclusão" data-content="Deseja realmente excluir?"><img src="img/excluir.png" width="20" alt="Excluir Lembrete" title="Excluir Lembrete"/></a>
            </td>
          </tr>
          <tr>
            <td width="128">Casa</td>
            <td width="20">
              <a href="#" data-toggle="popover" title="Confirmar Exclusão" data-content="Deseja realmente excluir?"><img src="img/editar.png" alt="Editar Lembrete" width="20" height="19" title="Editar Lembrete"/></a>
            </td>
            <td width="20">
              <a href="#" data-toggle="popover" title="Confirmar Exclusão" data-content="Deseja realmente excluir?"><img src="img/excluir.png" width="20" alt="Excluir Lembrete" title="Excluir Lembrete"/></a>
            </td>
          </tr>
        </table>
      </div>
    </div>
    <div id="map_canvas"></div>
  </body>
  
  <script type="text/javascript">
  
  	$(document).ready(function() {
		
        $('.excluir-popover').popover({
			title: 'Confirmar Exclusão',
			html: true,
			content: $('#div-exclusao').html(),
			trigger: 'focus',
			placement: 'right'
		});
		
		$('.editar-popover').popover({
			title: 'Editar Lembrete',
			html: true,
			content: $('#div-editar').html(),
			trigger: 'focus',
			placement: 'top'
		});
		
    });
  
  </script>
  
  <script type="text/javascript">
	$(document).ready(function(){
	 $('#imagem').on('change',function(){
		 $('#retornoImg').html('<img src="img/ajax-loader.gif" alt="Enviando..."/> Enviando...');
		 /* Efetua o Upload */
		 	$('#formularioImagem').ajaxForm({
			target:'#retornoImg' // o upload sera na div retornoImg
		 	}).submit();
		 
	 });
 })
 </script>

</html>