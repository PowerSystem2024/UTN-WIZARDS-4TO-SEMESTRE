// Importaciones
const express = require("express");
const cors = require("cors");
const path = require("path");
const { MercadoPagoConfig, Preference } = require("mercadopago");

// Inicializar Express
const app = express();

// ⚠️ IMPORTANTE: acá va tu Access Token (usa el de prueba primero)
const client = new MercadoPagoConfig({
  accessToken: "APP_USR-4620209687524993-090522-954308331bc7a890b41bc3d891cf90af-2675330292", 
});

const preference = new Preference(client);

// Middlewares
app.use(express.urlencoded({ extended: false }));
app.use(express.json());
app.use(cors());

// Servir carpeta client (HTML, CSS, JS, imágenes)
app.use(express.static(path.join(__dirname, "../client")));

// Ruta principal (index.html)
app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "../client/index.html"));
});

// Endpoint para crear preferencia de pago
app.post("/create_preference", async (req, res) => {
  try {
    const result = await preference.create({
      body: {
        items: [
          {
            title: req.body.description,
            unit_price: Number(req.body.price),
            quantity: Number(req.body.quantity),
            currency_id: "ARS",
          },
        ],
        back_urls: {
          success: "https://e-commerce-utn-wizards2025.netlify.app/",
          failure: "https://e-commerce-utn-wizards2025.netlify.app/",
          pending: "https://e-commerce-utn-wizards2025.netlify.app/",
        },
        auto_return: "approved",
      },
    });

    res.json({ id: result.id });
  } catch (error) {
    console.error("Error creando la preferencia:", error);
    res.status(500).json({ error: "No se pudo crear la preferencia" });
  }
});

// Endpoint para feedback de pago
app.get("/feedback", (req, res) => {
  res.json({
    Payment: req.query.payment_id,
    Status: req.query.status,
    MerchantOrder: req.query.merchant_order_id,
  });
});

// ✅ Endpoints de redirección desde Mercado Pago
app.get("/success", (req, res) => {
  res.send("✅ Pago aprobado. Gracias por tu compra. <a href='/'>Volver al inicio</a>");
});

app.get("/failure", (req, res) => {
  res.send("❌ El pago fue rechazado. <a href='/'>Volver a intentar</a>");
});

app.get("/pending", (req, res) => {
  res.send("⏳ El pago está pendiente. <a href='/'>Volver al inicio</a>");
});

// Iniciar servidor
const PORT = 8080;
app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT} 🚀`);
});