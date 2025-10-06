const express = require("express");
const app = express();
const cors = require("cors");
const mercadopago = require("mercadopago");
const path = require("path");

// REEMPLAZÁ <ACCESS_TOKEN> CON EL TUYO desde: https://developers.mercadopago.com/panel
mercadopago.configure({
	access_token: "APP_USR-7339090490718521-041205-10800f4415656dc1b8657ecbb745a4a96-72697550",
});

app.use(express.urlencoded({ extended: false }));
app.use(express.json());
app.use(cors());

// Servir archivos estáticos de la carpeta client

// Crear preferencia de pago
app.post("/create_preference", (req, res) => {
	let preference = {
		items: [
			{
				title: req.body.description,
				unit_price: Number(req.body.price),
				quantity: Number(req.body.quantity),
			}
		],
		back_urls: {
			"success": "http://localhost:8080",
			"failure": "http://localhost:8080",
			"pending": ""
		},
		auto_return: "approved",
	};

	mercadopago.preferences.create(preference)
		.then(function (response) {
			res.json({
				id: response.body.id
			});
		}).catch(function (error) {
			console.log(error);
			res.status(500).json({ error: "Error al crear la preferencia" });
		});
});

// Endpoint para feedback de MercadoPago
app.get('/feedback', function (req, res) {
	res.json({
		Payment: req.query.payment_id,
		Status: req.query.status,
		MerchantOrder: req.query.merchant_order_id
	});
});

// Levantar servidor en el puerto 8080
app.listen(8080, () => {
	console.log("🚀 The server is now running on http://localhost:8080");
});
