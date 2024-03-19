CREATE TABLE IF NOT EXISTS public.product
(
    product_id BIGINT PRIMARY KEY NOT NULL,
    product_name VARCHAR NOT NULL,
    product_price INTEGER NOT NULL,
    product_stock INTEGER NOT NULL
);

CREATE TYPE reservation_status AS ENUM ('未確定', '確定', 'キャンセル');

CREATE TABLE IF NOT EXISTS public.reservation
(
    reservation_id BIGINT PRIMARY KEY NOT NULL,
    customer_name VARCHAR NOT NULL,
    reservation_time TIMESTAMP WITH TIME ZONE NOT NULL,
    customer_tel VARCHAR,
    reservation_email VARCHAR NOT NULL,
    status reservation_status,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES public.user_table (user_id)
);

CREATE TABLE IF NOT EXISTS public.user_table
(
    user_id BIGINT PRIMARY KEY NOT NULL,
    user_name VARCHAR NOT NULL,
    user_password VARCHAR NOT NULL,
    user_tel VARCHAR NOT NULL,
    authority_kind VARCHAR NOT NULL,
    reservation_id BIGINT,
    FOREIGN KEY (reservation_id) REFERENCES public.reservation (reservation_id)
);

-- reservation_product 中間テーブルの作成
CREATE TABLE IF NOT EXISTS public.reservation_product
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    reservation_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    purchased_quantity INT NOT NULL,
    FOREIGN KEY (reservation_id) REFERENCES public.reservation (reservation_id),
    FOREIGN KEY (product_id) REFERENCES public.product (product_id)
);