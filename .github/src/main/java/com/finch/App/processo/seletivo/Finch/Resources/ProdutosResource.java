package com.finch.App.processo.seletivo.Finch.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finch.App.processo.seletivo.Finch.Model.Lanche;
import com.finch.App.processo.seletivo.Finch.Model.LancheIngrediente;
import com.finch.App.processo.seletivo.Finch.Model.Pedido;
import com.finch.App.processo.seletivo.Finch.Model.Ingrediente;
import com.finch.App.processo.seletivo.Finch.Repository.LancheRepository;
import com.finch.App.processo.seletivo.Finch.Repository.IngredienteRepository;
import com.finch.App.processo.seletivo.Finch.Repository.LancheIngredienteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api")
@Api(value="API REST Produtos")
public class ProdutosResource {

	@Autowired
	LancheRepository lancheRepository;
	
	@Autowired
	IngredienteRepository ingredienteRepository;
	
	@Autowired
	LancheIngredienteRepository lancheIngredienteRespository;
	
	@ApiOperation(value="Metodo responsavel por listar todos os lanches da StartUp")
	@GetMapping("/lanches")
	public List<Lanche> listarLanche(){
		return lancheRepository.findAll();
	}
	
	@ApiOperation(value="Metodo responsavel por listar todos os ingredientes dos lanches da StartUp")
	@GetMapping("/ingredientes")
	public List<Ingrediente> listarIngrediente(){
		return ingredienteRepository.findAll();
	}
	
	@ApiOperation(value="Metodo responsavel por calcular o valor do lanche")
	@PostMapping("/pedido")
	public double calcularValorPedido(@RequestBody Pedido pedido) {
		Optional<Ingrediente> ingrediente;
		Optional<Lanche> lanche = lancheRepository.findById(pedido.getId_lanche());
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		double valor = 0;
		int porcao = 0;
		
		if (lanche.isPresent()) {
			List<LancheIngrediente> lancheIngrediente = lancheIngredienteRespository.findIngredienteByLanche(pedido.getId_lanche());
			ingrediente = ingredienteRepository.findById(pedido.getId_ingrediente());
			
			//Adiciona ingrediente extra
			if(pedido.getId_ingrediente() != 3 && pedido.getId_ingrediente() != 5) {
				if (ingrediente.isPresent()) {
					ingredientes.add(ingrediente.get());
					valor += ingrediente.get().getValor() * pedido.getQtde();
				}
			}
			
			//Monta lista padrão de ingredientes
			for(int i = 0; i < lancheIngrediente.size(); i++) {
				ingredientes.add(lancheIngrediente.get(i).getIngrediente());
				valor += lancheIngrediente.get(i).getIngrediente().getValor();
			}
			
			
			//Verifica se lanche é light
			if(lanche.get().isLight(ingredientes))
				valor = valor * 0.9;
			else if(pedido.getId_ingrediente() == 3 || pedido.getId_ingrediente() == 5) { //Muita Carne - 3 ou Muita Queijo - 5
				if(pedido.getQtde() % 3 == 0) {
					porcao = (pedido.getQtde()/3) * 2;
					valor += porcao * ingrediente.get().getValor();
				} else {
					valor += pedido.getQtde() * ingrediente.get().getValor();
				}
			}
			else { //não é nenhuma promoção
				if(ingrediente.isPresent())
					valor += pedido.getQtde() * ingrediente.get().getValor();
			}
		}
		return valor;
	}
}
