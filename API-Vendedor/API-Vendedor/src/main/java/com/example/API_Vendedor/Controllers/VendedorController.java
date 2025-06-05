package com.example.API_Vendedor.Controllers;

import com.example.API_Vendedor.models.VendedorModelo;
import com.example.API_Vendedor.Service.VendedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vendedores", produces = "application/json", consumes = "application/json")
@RequiredArgsConstructor
public class VendedorController {

    private final VendedorService vendedorService;

    // POST /vendedores  - Crear un nuevo vendedor
    @PostMapping
    public ResponseEntity<VendedorModelo> crearVendedor(@RequestBody VendedorModelo vendedor) {
        VendedorModelo creado = vendedorService.crearVendedor(vendedor);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    // GET /vendedores/{id} - Obtener vendedor por id
    @GetMapping("/{id}")
    public ResponseEntity<VendedorModelo> obtenerVendedor(@PathVariable Integer id) {
        VendedorModelo vendedor = vendedorService.obtenerPorId(id);
        return ResponseEntity.ok(vendedor);
    }

    // PUT /vendedores/{id} - Actualizar vendedor
    @PutMapping("/{id}")
    public ResponseEntity<VendedorModelo> actualizarVendedor(@PathVariable Integer id, @RequestBody VendedorModelo datos) {
        VendedorModelo actualizado = vendedorService.actualizarVendedor(id, datos);
        return ResponseEntity.ok(actualizado);
    }

    // GET /vendedores/sucursal/{id} - Obtener vendedores por sucursal
    @GetMapping("/sucursal/{idSucursal}")
    public ResponseEntity<List<VendedorModelo>> obtenerPorSucursal(@PathVariable Integer idSucursal) {
        List<VendedorModelo> vendedores = vendedorService.obtenerPorSucursal(idSucursal);
        return ResponseEntity.ok(vendedores);
    }
}
