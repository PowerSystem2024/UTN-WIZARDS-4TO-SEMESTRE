--
-- PostgreSQL database dump
--

\restrict d8KOhKLs4uelelDctf4Z8OWh3kw6wgNqFwodbja7a3U4mLcohoJfveGxc8gZlPJ

-- Dumped from database version 15.14
-- Dumped by pg_dump version 15.14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: tareas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tareas (
    id integer NOT NULL,
    titulo character varying(255) NOT NULL,
    descripcion text,
    usuario_id integer,
    titulo_usuario_unique text
);


ALTER TABLE public.tareas OWNER TO postgres;

--
-- Name: tareas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tareas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tareas_id_seq OWNER TO postgres;

--
-- Name: tareas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tareas_id_seq OWNED BY public.tareas.id;


--
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id integer NOT NULL,
    nombre character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    fecha_registro timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    gravatar character varying(255)
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- Name: usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_id_seq OWNER TO postgres;

--
-- Name: usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;


--
-- Name: tareas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tareas ALTER COLUMN id SET DEFAULT nextval('public.tareas_id_seq'::regclass);


--
-- Name: usuarios id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);


--
-- Data for Name: tareas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tareas (id, titulo, descripcion, usuario_id, titulo_usuario_unique) FROM stdin;
19	tarea pepe13	tiene muchas cosas, es un tipo ocupado	26	tarea pepe13-26
22	jose@josefo.com	\N	68	jose@josefo.com-68
60	DBD	No morir en el intento	25	DBD-25
61	123		25	123-25
72	qwe	wqeqweqwe	25	qwe-25
74	1234	213123	25	1234-25
68	mmmmaaaaeeeeee	mmams	25	mmmmaaaaeeeeee-25
2	Tarea 2	mi descripcion	25	Tarea 2-25
9	Tarea 3	mi descripcion	26	Tarea 3-26
10	Tarea 7	mi descripcion2	25	Tarea 7-25
11	Tarea 5	mi descripcion2	26	Tarea 5-26
13	Tarea 13	mi descripcion3	25	Tarea 13-25
20	pepe13@mail.com	123456	26	pepe13@mail.com-26
21	mi mama me mima		25	mi mama me mima-25
75	123456	sadsadasd	25	123456-25
77	123456		26	123456-26
79	Mi mama me ama		26	Mi mama me ama-26
80	1234	213123	74	1234-74
81	1231323123	21321312	74	1231323123-74
82	123456	123123123	74	123456-74
83	sdasdasdsd	sadadasd	74	sdasdasdsd-74
84	 aria-label="Ir a Perfil"	dsfsdf	74	 aria-label="Ir a Perfil"-74
85	 asd	sda	74	 asd-74
87	12344		74	12344-74
17	tarea usuario 20	muchas cosas	25	tarea usuario 20-25
\.


--
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (id, nombre, email, password, fecha_registro, fecha_actualizacion, gravatar) FROM stdin;
4	Test	pepe@mail.com	123456	2025-10-27 21:21:32.286592	2025-10-27 21:21:32.286592	\N
9	Test	pepe2@mail.com	123456	2025-10-27 21:40:51.066743	2025-10-27 21:40:51.066743	\N
11	Test3	pepe3@mail.com	$2b$10$5I4VEOjaeln0yGVlXDNrtudm6B0W2L7cTpmo.Bn2Umhf/ZpON0AXC	2025-10-27 21:47:20.321747	2025-10-27 21:47:20.321747	\N
13	Test4	pepe4@mail.com	$2b$10$CCX127Z12iM2Ys7DPr2euOBZTaG2xEu4UDpLxNnHyJWKSxWnvQF9m	2025-10-28 12:42:36.473316	2025-10-28 12:42:36.473316	\N
14	Test5	pepe5@mail.com	$2b$10$vx7oq5tz0KZz85y/iVoX9.xvPESz0xYjDkBRwSAJ5.cw1FiospbdW	2025-10-28 12:46:13.059951	2025-10-28 12:46:13.059951	\N
15	Test6	pepe6@mail.com	$2b$10$U/C5mQYkAZZJp61ViLgMtuDYSrAxpr/inHPeYjShTa9X0gUHqR3d.	2025-10-28 12:53:29.980553	2025-10-28 12:53:29.980553	\N
16	Test7	pepe8@mail.com	$2b$10$P5EzqaTE/jayk9.W1tu3Ye7MW03hZvEYYaK1pwK76oJZU3bE08Z8u	2025-10-28 12:58:07.353238	2025-10-28 12:58:07.353238	\N
18	Test9	pepe9@mail.com	$2b$10$eESeb/J0WP.ff419XaQXR.UxfrrZ0BLNRStPojQMhPUBToUfpi6vS	2025-10-28 13:00:14.820627	2025-10-28 13:00:14.820627	\N
19	Test10	pepe10@mail.com	$2b$10$veEgN4DHKn84dLVCNV.VXeJqMdldtFRyJuGRO1fGMsUc/g3kjNn1.	2025-10-28 13:02:36.968336	2025-10-28 13:02:36.968336	\N
20	Test11	pepe11@mail.com	$2b$10$0ykr6ajAJ8eMW/CDiC9Xv.U04cVbfB9jmn2ZkxtN4miQiRTF7U0Ji	2025-10-28 13:03:07.149068	2025-10-28 13:03:07.149068	\N
26	pepe	pepe13@mail.com	$2b$10$2dkuU/T7128hx1dBHgp3Ue1rC1mCnmIjXUCOvAxg5qO8E439ZFOCq	2025-10-28 20:01:20.876733	2025-10-28 20:01:20.876733	https://gravatar.com/avatar/cefa2d655bf3886aab9afee5fdc18e61
27	pepe	pepe14@mail.com	$2b$10$1Om8G6i7cQPy1jcqDrQLx.WByQcyXk5dB1NxdGFBb2DMHt.8NaAjq	2025-10-28 20:07:59.355653	2025-10-28 20:07:59.355653	https://gravatar.com/avatar/88087aad8c50d9a32e89ccf1aceb3d1a
29	achalay	my@ads-asd.com	$2b$10$qcGWGdnRZ35oa0S.aARQQui6oz7.xgeOnd3vDYekVjAroY5rtCT1y	2025-10-29 02:41:33.095083	2025-10-29 02:41:33.095083	https://gravatar.com/avatar/b056c9d318686921532b3da73cb8adf9
31	achalay	my2@ads-asd.com	$2b$10$YEpifbxpehZcyoJBu0WDUuH.S5N8lzHl/nDzNDO188rFGNwaUiv8q	2025-10-29 02:43:16.701413	2025-10-29 02:43:16.701413	https://gravatar.com/avatar/ab99a12773ed69730366b6f95ab0e982
32	achalay	my5@ads-asd.com	$2b$10$Uo4Yo60CAb36JYl7tJnFXulz4r6h0HYkkoNQwP.076TrTkk5RT0Hu	2025-10-29 02:48:52.532512	2025-10-29 02:48:52.532512	https://gravatar.com/avatar/c677de121c373da62b0ad29c1e06bfed
33	achalay	my6@ads-asd.com	$2b$10$llJ48sVD3cUDk8yErkb5NOCPqeABGs/YDbVUFjjB32qCNhPS9QAjG	2025-10-29 02:48:59.047648	2025-10-29 02:48:59.047648	https://gravatar.com/avatar/4055e711e533814a74e189d899b7187c
34	achalay	my7@ads-asd.com	$2b$10$T5bhB8UggAFLRXNNzefzDu16SrqeeiDzehcw22bcTPiOBtXMzw7Nu	2025-10-29 02:51:29.083711	2025-10-29 02:51:29.083711	https://gravatar.com/avatar/ec66c61144b41f83d4a90b9664aea1a5
35	Gustavo Marcelo	asddd@mailing.com	$2b$10$f0s8bvcsQZJvEyHECv/iOu.VFcQL5FWdNV8/VtseDSPwe3.iqujSC	2025-10-29 02:53:04.139764	2025-10-29 02:53:04.139764	https://gravatar.com/avatar/b7915249138e5a21c5ee8fb8c53d1af4
36	Gustavo Marcelo	infeli2@chumail.com	$2b$10$nWUG/Wjhg0zVCrXmGt8xAO5/m1AumgLDbq6Tmn45QaGXPXumo8VxS	2025-10-29 03:04:45.176355	2025-10-29 03:04:45.176355	https://gravatar.com/avatar/f384cc9c671ec322951f13b6eca60378
47	achalay200	my200@ads-asd.com	$2b$10$XZx1pC5zzSBUfjM9r4cnwuVyX6pr1X7i2BzdqjdvFdoDGFhf35KhW	2025-10-29 10:17:55.091959	2025-10-29 10:17:55.091959	https://gravatar.com/avatar/204c24319fb5e9f4e9e4f4f766a0e789
49	achalay2001	my2001@ads-asd.com	$2b$10$IQtdfvGPI1Zr0GQtvJ0JMun3mGXD9H0F6zNiQtW85zQzClAQ8AhwS	2025-10-29 10:18:10.604974	2025-10-29 10:18:10.604974	https://gravatar.com/avatar/19ca99fb4974f4ab6c302995facc41de
50	achalay213	my2001111@ads-asd.com	$2b$10$VhuPg3wkPbqS7RK/9F/61.KwP30mE3o7Bw2IWtyH/ei4g6GnqGroa	2025-10-29 11:01:57.044675	2025-10-29 11:01:57.044675	https://gravatar.com/avatar/59c7307d4fb104488e87a7e0216d6dde
51	achalay21312	my200112@ads-asd.com	$2b$10$dC7mIs3DrHtZfdh0/JOh3exjo/SSzqyLhGsl5jd1It1CbBJy3WE2O	2025-10-29 11:05:21.215527	2025-10-29 11:05:21.215527	https://gravatar.com/avatar/7f7938cbcb9ed687f39c2dfa65d2844f
52	Gustavo Marcelo	capovil@gmail.com	$2b$10$2ZMSsU7BuNQeVRff9oTuT.3QCGUk1FylK8pv6wsulZHqXwXg/oKMG	2025-10-29 11:09:22.55345	2025-10-29 11:09:22.55345	https://gravatar.com/avatar/681bd62e98744ad1eb2ca129a465b132
53	asd	asddd1@mailing.com	$2b$10$jr9prUhFOtVBKb2N5GWHbOx0W4NAG5wFvJc18LByuS5Zi8UujG916	2025-10-29 11:12:37.737325	2025-10-29 11:12:37.737325	https://gravatar.com/avatar/d3a70a53e367f682f64ad9ecf91fb74e
25	pepe	pepe12@mail.com	$2b$10$FBul40Q9YpmbK9HhwXReVevNVrM8kdM/464vO32oiMbp1nA5.ewDq	2025-10-28 19:15:18.370463	2025-11-05 13:24:21.760609	\N
54	asdasdasd	sasdsadasd@sdas.com	$2b$10$XxCq4U4ia3uUzyc7MHqe8u9KkGR.Zq0Cvsvk9A09mYtX3AsXM9Dqi	2025-10-29 11:14:35.248247	2025-10-29 11:14:35.248247	https://gravatar.com/avatar/6cf0479f3f36bcdfa32b141aea618478
55	asssssassss	dasdsad@asdas.com	$2b$10$iGDTQXc2yAH9cGNKHd929Ok1It8RwHblYflJJm2LJGwtCGtcoTg8u	2025-10-29 11:18:20.024948	2025-10-29 11:18:20.024948	https://gravatar.com/avatar/bdd51d1ef3f2d72565d9dd41f74b267c
56	Gustavo Marcelo	pancholoco@gmail.dot	$2b$10$JCKd83nnbdE63v/owSkd6eeqoVLE9eFNcH7PgoNnG.snbYNMmij.O	2025-10-29 11:27:05.22144	2025-10-29 11:27:05.22144	https://gravatar.com/avatar/7fb6325ca6d8bda0bdb8b08e51fca524
57	pepe252	pepe252@mail	$2b$10$BsS6kzNyS0nBWitt0X3ssebRKmwkrSdEm3WtGzA.BVijbGx6jMoqi	2025-10-29 12:43:06.899268	2025-10-29 12:43:06.899268	https://gravatar.com/avatar/6799916aeab33da359f2754376d4650e
59	pepe253	pepe253@mail.com	$2b$10$4RP6W4i7aqTHXh6fVJIB6eP4RnDvgJJia9Nd3WzR7JjDLirf31K1S	2025-10-29 12:44:31.71562	2025-10-29 12:44:31.71562	https://gravatar.com/avatar/2fd6a4350ea4b866a10bbdb010799c7e
60	pepe254	pepe254@mail.com	$2b$10$gKO6JzSZb34mosCDXWE5z.XLg5MYn/RIVbOZ3hAAv8GawdZNCeYm2	2025-10-29 12:47:53.848615	2025-10-29 12:47:53.848615	https://gravatar.com/avatar/e8c216858e7c2d18093b71fa5c882179
62	pepe15	pepe15@mail.com	$2b$10$RT84ZMQ8czbIVr2tyeApHOoJzxTWBFzkuOhLpr9jm1oOZ.UDqZkom	2025-10-29 14:07:46.665756	2025-10-29 14:07:46.665756	https://gravatar.com/avatar/46324e2e4b3c8cae7b6426ec01617fad
67	Jose1	as1212@josefo.com	$2b$10$4OARwHL8ANH5R/w8dayuPuqea.gCfUJypuaK6OwHOYHuDPJ7YKvGi	2025-10-29 15:16:12.480096	2025-10-29 15:16:12.480096	https://gravatar.com/avatar/1cd6ba14c5de136b8b79d7e6906dc7e0
68	Jose	jose@josefo.com	$2b$10$oI/SfWkYo5VwbrqJtHkr.um1cA60fs.Di9vsq9OLWgvPkfh5pad.O	2025-10-29 15:18:14.367492	2025-10-29 15:18:14.367492	https://gravatar.com/avatar/baf715198d412f2229c730df9122c7f8
71	gaston	gas@ton.com	$2b$10$wphmH/tp5B4AdHIM6hCufe1HlA1v1DTvDekDKJAHHsCaTUOm5t1Qu	2025-10-29 16:10:56.477111	2025-10-29 16:10:56.477111	https://gravatar.com/avatar/1452c11fb107e8dd80d25fc142d1e7d9
73	Gustavo Marcelo	pepe12111@mail.com	$2b$10$7rxEAemioUBH4ZoeZZ.f1.5mP7qZ1mHb7v9wVfCYwLRNG.ge5ijXG	2025-10-29 22:54:04.928939	2025-10-29 22:54:04.928939	https://gravatar.com/avatar/0dd29f1b2666d2e3361e458984a92ac9
74	Pepe Infinity	pepeinfinity@mail.com	$2b$10$ekVSN0Ed4zDOt1.bTATLietPKUH44vRdJ7g8qUpR6YGJOyp0VYcnu	2025-11-04 14:17:07.388576	2025-11-04 14:17:07.388576	https://gravatar.com/avatar/681e5e36a18e4efab499658186c76abb
75	Last Pepe	lastpepe@mail.com	$2b$10$DDDC2Ex6bh95duS48b3OjO1MG/lzNMQ/cZ0ZvbD5bpIVvq3RKXrs2	2025-11-04 14:59:14.485303	2025-11-04 14:59:14.485303	https://gravatar.com/avatar/5633964387c4bb5c8b8c81a4c9fbce33
76	Pepazo	pepe252@mail.com	$2b$10$Ja/CyOfp9MWrRuUV3QRCFuLsu9YWSu0cPmV3rGC0BTYCkpAD/DSji	2025-11-05 13:52:52.558514	2025-11-05 13:52:52.558514	https://gravatar.com/avatar/2155648ea15d0d77800ae1bb8bbe28de
\.


--
-- Name: tareas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tareas_id_seq', 88, true);


--
-- Name: usuarios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_id_seq', 76, true);


--
-- Name: tareas tareas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tareas
    ADD CONSTRAINT tareas_pkey PRIMARY KEY (id);


--
-- Name: tareas tareas_titulo_usuario_unique_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tareas
    ADD CONSTRAINT tareas_titulo_usuario_unique_key UNIQUE (titulo_usuario_unique);


--
-- Name: usuarios usuarios_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_email_key UNIQUE (email);


--
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);


--
-- Name: tareas tareas_usuario_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tareas
    ADD CONSTRAINT tareas_usuario_id_fkey FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id);


--
-- PostgreSQL database dump complete
--

\unrestrict d8KOhKLs4uelelDctf4Z8OWh3kw6wgNqFwodbja7a3U4mLcohoJfveGxc8gZlPJ

