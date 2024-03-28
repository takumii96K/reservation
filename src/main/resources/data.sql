INSERT INTO public.image (image_name, image_url, image_type) VALUES
                                                                 ('スマイル', '/images/4.jpg', 'image/jpeg'),
                                                                 ('ドーナッツ', '/images/6.jpg', 'image/jpeg'),
                                                                 ('アポーパイ', '/images/1.jpg', 'image/jpeg'),
                                                                 ('チキンナゲット', '/images/5.jpg', 'image/jpeg'),
                                                                 ('ホットドッグ', '/images/8.jpg', 'image/jpeg'),
                                                                 ('ポテト', '/images/9.jpg', 'image/jpeg'),
                                                                 ('ミックスポップコーン', '/images/10.jpg', 'image/jpeg'),
                                                                 ('ベジタリアンバーガー', '/images/7.jpg', 'image/jpeg'),
                                                                 ('カリカリベーコンバーガー', '/images/3.jpg', 'image/jpeg'),
                                                                 ('ビックマック', '/images/2.jpg', 'image/jpeg'),
                                                                 ('淡路島バーガー', '/images/11.jpg', 'image/jpeg'),
                                                                 ('ジャパニーズバーガー', '/images/12.jpg', 'image/jpeg'),
                                                                 ('チャイニーズバーガー', '/images/13.jpg', 'image/jpeg'),
                                                                 ('北欧バーガー', '/images/14.jpg', 'image/jpeg'),
                                                                 ('イタリアンバーガー', '/images/15.jpg', 'image/jpeg'),
                                                                 ('イングランドバーガー', '/images/16.jpg', 'image/jpeg'),
                                                                 ('キッズバーガー', '/images/17.jpg', 'image/jpeg'),
                                                                 ('コーラ', '/images/18.jpg', 'image/jpeg'),
                                                                 ('Heineken', '/images/19.jpg', 'image/jpeg'),
                                                                 ('ジンジャエール', '/images/20.jpg', 'image/jpeg'),
                                                                 ('マルゲリータ', '/images/21.jpg', 'image/jpeg'),
                                                                 ('ペパロニピザ', '/images/22.jpg', 'image/jpeg'),
                                                                 ('メキシカンピザ', '/images/23.jpg', 'image/jpeg'),
                                                                 ('ピザトースト', '/images/24.jpg', 'image/jpeg');





INSERT INTO public.product (product_name, product_price, product_stock, category, image_id) VALUES
                                                                                                ('スマイル', 650, 70, 'FOOD', 1),
                                                                                                ('ドーナッツ', 750, 50, 'FOOD', 2),
                                                                                                ('アポーパイ', 550, 90, 'FOOD', 3),
                                                                                                ('チキンナゲット', 700, 60, 'FOOD', 4),
                                                                                                ('ホットドッグ', 850, 30, 'FOOD', 5),
                                                                                                ('ポテト', 900, 20, 'SIDE', 6),
                                                                                                ('ミックスポップコーン', 950, 10, 'SIDE', 7),
                                                                                                ('ベジタリアンバーガー', 800, 40, 'FOOD', 8),
                                                                                                ('カリカリベーコンバーガー', 600, 80, 'FOOD', 9),
                                                                                                ('ビックマック', 500, 100, 'FOOD', 10),
                                                                                                ('淡路島バーガー', 500, 100, 'FOOD', 11),
                                                                                                ('ジャパニーズバーガー', 550, 90, 'FOOD', 12),
                                                                                                ('チャイニーズバーガー', 600, 80, 'FOOD', 13),
                                                                                                ('北欧バーガー', 650, 70, 'FOOD', 14),
                                                                                                ('イタリアンバーガー', 700, 60, 'FOOD', 15),
                                                                                                ('イングランドバーガー', 750, 50, 'FOOD', 16),
                                                                                                ('キッズバーガー', 800, 40, 'FOOD', 17),
                                                                                                ('コーラ', 850, 30, 'DRINK', 18),
                                                                                                ('Heineken', 900, 20, 'DRINK', 19),
                                                                                                ('ジンジャエール', 950, 10, 'DRINK', 20),
                                                                                                ('マルゲリータ', 900, 20, 'FOOD', 21),
                                                                                                ('ペパロニピザ', 900, 20, 'FOOD', 22),
                                                                                                ('メキシカンピザ', 900, 20, 'FOOD', 23),
                                                                                                ('ピザトースト', 900, 20, 'FOOD', 24);






INSERT INTO reservation (customer_name, reservation_time, customer_tel, reservation_email, reservation_status) VALUES
                                                                                                                       ('山田太郎', '2024-04-01 12:00:00', '080-1234-5678', 'yamada@example.com', '未確定'),
                                                                                                                       ('鈴木一郎', '2024-04-02 13:30:00', '080-8765-4321', 'suzuki@example.com', '確定'),
                                                                                                                       ('佐藤花子', '2024-04-03 15:00:00', '090-1122-3344', 'sato@example.com', '確定'),
                                                                                                                       ('田中次郎', '2024-04-04 17:45:00', '070-5566-7788', 'tanaka@example.com', 'キャンセル'),
                                                                                                                       ('伊藤桃子', '2024-04-05 19:30:00', '060-9988-7766', 'ito@example.com', '未確定');


-- 中間テーブルreservation_productにデータを挿入
INSERT INTO reservation_product (reservation_id, product_id, purchased_quantity) VALUES
                                                                           (1, 5, 2), -- 山田太郎がスパイシーチキンバーガーを2個予約
                                                                           (1, 4, 1), -- 山田太郎がりんご飴を1個予約
                                                                           (2, 1, 3), -- 鈴木一郎がたこ焼きを3個予約
                                                                           (2, 2, 2), -- 鈴木一郎が焼きそばを2個予約
                                                                           (3, 3, 4), -- 佐藤花子がはしまきを4個予約
                                                                           (4, 2, 1), -- 田中次郎が焼きそばを1個予約
                                                                           (4, 3, 2), -- 田中次郎がはしまきを2個予約
                                                                           (5, 5, 1);

-- user_tableにデータを挿入
INSERT INTO user_table (authority_kind, user_name, user_password, user_tel) VALUES
                                                                                         ('1', 'yamataro', 'taro-y111', '080-1234-5678'),
                                                                                         ('1', 'suzuki1', 'ichiro51', '080-8765-4321'),
                                                                                         ('1', 'komomo110', '110momoko', '060-9988-7766');

