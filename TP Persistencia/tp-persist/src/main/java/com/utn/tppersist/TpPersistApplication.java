package com.utn.tppersist;

import com.utn.tppersist.entities.*;
import com.utn.tppersist.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class TpPersistApplication {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    RubroRepository rubroRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    DetallePedidoRepository detallePedidoRepository;
    @Autowired
    DomicilioRepository domicilioRepository;
    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    ProductoRepository productoRepository;


    public static void main(String[] args) {
        SpringApplication.run(TpPersistApplication.class, args);
        System.out.println("Persistencia funcionando correctamente");
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            System.out.println("Aplicación funcionando");

            Rubro rubro = Rubro.builder()
                    .denominacion("Comida rápida")
                    .build();

            Producto producto1 = Producto.builder()
                    .foto("pizza.jpg")
                    .denominacion("Pizza")
                    .tipo(Producto.Tipo.manufacturado)
                    .receta("receta_pizza_link")
                    .tiempoEstimadoCocina(30)
                    .precioVenta(730.00)
                    .precioCompra(500.00)
                    .stockActual(200)
                    .stockMinimo(50)
                    .unidadMedida("por unidad")
                    .build();
            productoRepository.save(producto1);

            Producto producto2 = Producto.builder()
                    .foto("pancho.jpg")
                    .denominacion("Pancho")
                    .tipo(Producto.Tipo.manufacturado)
                    .receta("receta_pancho_link")
                    .tiempoEstimadoCocina(5)
                    .precioVenta(200.00)
                    .precioCompra(50.00)
                    .stockActual(100)
                    .stockMinimo(50)
                    .unidadMedida("por unidad")
                    .build();
            productoRepository.save(producto2);

            rubro.agregarProductos(producto1);
            rubro.agregarProductos(producto2);
            rubroRepository.save(rubro);

            DetallePedido detallePedido1 = DetallePedido.builder()
                    .cantidad(2)
                    .subtotal(1460.00)
                    .build();
            detallePedido1.setProducto(producto1);
            detallePedidoRepository.save(detallePedido1);

            DetallePedido detallePedido2 = DetallePedido.builder()
                    .cantidad(3)
                    .subtotal(600.00)
                    .build();
            detallePedido2.setProducto(producto2);
            detallePedidoRepository.save(detallePedido2);

            Pedido pedido = Pedido.builder()
                    .fecha("07/09/2023")
                    .estado(Pedido.Estado.preparacion)
                    .horaEstimadaEntrega(LocalTime.of(15,30))
                    .tipoEnvio(Pedido.TipoEnvio.delivery)
                    .total(2160.00)
                    .build();
            pedido.agregarDetalles(detallePedido1);
            pedido.agregarDetalles(detallePedido2);

            Factura factura = Factura.builder()
                    .fecha(LocalDate.of(2023,9,7))
                    .numero(00012)
                    .descuento(0.0)
                    .formaPago(Factura.FormaPago.mercadoPago)
                    .total(2160)
                    .build();
            facturaRepository.save(factura);

            pedido.setFactura(factura);
            pedidoRepository.save(pedido);

            Cliente cliente = Cliente.builder()
                    .nombre("Laura")
                    .apellido("Pérez")
                    .mail("lauraperez@mail.com")
                    .telefono("+54 121 2345678")
                    .build();
            cliente.agregarPedidos(pedido);
            clienteRepository.save(cliente);

            Usuario usuario = Usuario.builder()
                    .nombre("Andrés")
                    .rol("repartidor")
                    .password("abc123")
                    .build();
            usuario.agregarPedidos(pedido);
            usuarioRepository.save(usuario);

            Domicilio domicilio = Domicilio.builder()
                    .calle("Av. San Martín")
                    .numero("950")
                    .localidad("Tunuyán")
                    .build();
            domicilio.agregarPedidos(pedido);
            domicilio.setCliente(cliente);
            domicilioRepository.save(domicilio);

            // Recupero los objetos de la base de datos:

            System.out.println("Objetos recuperados de la base de datos");

            Usuario usuarioRecuperado = usuarioRepository.findById(usuario.getId()).orElse(null);
            if (usuarioRecuperado != null) {
                usuarioRecuperado.mostrarPedidos();
            }

            Cliente clienteRecuperado = clienteRepository.findById(cliente.getId()).orElse(null);
            if (clienteRecuperado != null) {
                clienteRecuperado.mostrarPedidos();
            }

            Domicilio domicilioRecuperado = domicilioRepository.findById(domicilio.getId()).orElse(null);
            if (domicilioRecuperado != null) {
                System.out.println("Cliente asociado al domicilio: " + domicilioRecuperado.getCliente());
                domicilioRecuperado.mostrarPedidos();
            }

            Pedido pedidoRecuperado = pedidoRepository.findById(pedido.getId()).orElse(null);
            if (pedidoRecuperado != null) {
                pedidoRecuperado.mostrarDetalles();
            }

            DetallePedido detallePedidoRecuperado1 = detallePedidoRepository.findById(detallePedido1.getId()).orElse(null);
            if (detallePedidoRecuperado1 != null) {
                System.out.println("Detalle del Pedido");
                System.out.println("Producto: " + detallePedidoRecuperado1.getProducto());
            }

            DetallePedido detallePedidoRecuperado2 = detallePedidoRepository.findById(detallePedido2.getId()).orElse(null);
            if (detallePedidoRecuperado2 != null) {
                System.out.println("Detalle del Pedido");
                System.out.println("Producto: " + detallePedidoRecuperado2.getProducto());
            }

            Rubro rubroRecuperado = rubroRepository.findById(rubro.getId()).orElse(null);
            if (rubroRecuperado != null) {
                rubroRecuperado.mostrarProductos();
            }

        };
    }
}
