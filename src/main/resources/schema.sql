CREATE TABLE IF NOT EXISTS public.product
(
    product_id SERIAL NOT NULL,
    product_name character varying COLLATE pg_catalog."default" NOT NULL,
    product_price integer NOT NULL,
    product_stock integer NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (product_id)
);



CREATE TABLE IF NOT EXISTS public.reservation
(
    reservation_id bigint NOT NULL,
    customer_name character varying COLLATE pg_catalog."default" NOT NULL,
    reservation_time time with time zone NOT NULL,
    customer_tel character varying COLLATE pg_catalog."default",
    reservation_email character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id)
);

CREATE TABLE IF NOT EXISTS public.user_table
(
    user_id bigint NOT NULL,
    user_name character varying COLLATE pg_catalog."default" NOT NULL,
    user_password character varying COLLATE pg_catalog."default" NOT NULL,
    user_tel character varying COLLATE pg_catalog."default" NOT NULL,
    authority_kind character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_table_pkey PRIMARY KEY (user_id)
);

-- reservation_product 中間テーブルの作成
CREATE TABLE IF NOT EXISTS reservation_product (
                                                   reservation_id bigint NOT NULL,
                                                   product_id SERIAL NOT NULL,
                                                   FOREIGN KEY (reservation_id) REFERENCES reservation (reservation_id),
                                                   FOREIGN KEY (product_id) REFERENCES product (product_id),
                                                   PRIMARY KEY (reservation_id, product_id)
);