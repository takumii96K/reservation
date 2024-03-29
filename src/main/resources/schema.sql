CREATE TABLE IF NOT EXISTS public.product
(
    product_id SERIAL PRIMARY KEY NOT NULL,
    product_name VARCHAR NOT NULL,
    product_price INTEGER NOT NULL,
    product_stock INTEGER NOT NULL,
    category VARCHAR,
    image_id BIGINT,
    FOREIGN KEY (image_id) REFERENCES public.image (image_id)
);

CREATE TABLE IF NOT EXISTS public.reservation
(
    reservation_id SERIAL PRIMARY KEY NOT NULL,
    customer_name VARCHAR NOT NULL,
    reservation_time TIMESTAMP WITH TIME ZONE NOT NULL,
    customer_tel VARCHAR,
    reservation_email VARCHAR NOT NULL,
    user_id BIGINT,
    reservation_status VARCHAR NOT NULL,
    FOREIGN KEY (user_id) REFERENCES public.user_table (user_id)
);

CREATE TABLE IF NOT EXISTS public.user_table
(
    user_id SERIAL PRIMARY KEY NOT NULL,
    user_name VARCHAR NOT NULL,
    user_password VARCHAR NOT NULL,
    user_tel VARCHAR NOT NULL,
    authority_kind VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS public.reservation_product
(
    id SERIAL PRIMARY KEY,
    reservation_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    purchased_quantity INT NOT NULL,
    FOREIGN KEY (reservation_id) REFERENCES public.reservation (reservation_id),
    FOREIGN KEY (product_id) REFERENCES public.product (product_id)
);

CREATE TABLE IF NOT EXISTS public.image
(
    image_id SERIAL PRIMARY KEY,
    image_name VARCHAR(255),
    image_path VARCHAR(255),
    image_type VARCHAR(255)
);
