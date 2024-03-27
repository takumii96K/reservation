INSERT INTO product (product_name, product_url, product_price, product_stock) VALUES
                                                                                  ('スマイル', '/images/4.jpg', 650, 70),
                                                                                  ('ドーナッツ', '/images/6.jpg', 750, 50),
                                                                                  ('アポーパイ', '/images/2.jpg', 550, 90),
                                                                                  ('チキンナゲット', '/images/5.jpg', 700, 60),
                                                                                  ('ホットドッグ', '/images/8.jpg', 850, 30),
                                                                                  ('ポテト', '/images/9.jpg', 900, 20),
                                                                                  ('ミックスポップコーン', '/images/10.jpg', 950, 10),
                                                                                  ('ベジタリアンバーガー', '/images/7.jpg', 800, 40),
                                                                                  ('カリカリベーコンバーガー', '/images/3.jpg', 600, 80),
                                                                                  ('ビックマック', '/images/bigmac.png', 500, 100),
                                                                                  ('淡路島バーガー', '/images/11.jpg', 500, 100),
                                                                                  ('ジャパニーズバーガー', '/images/12.jpg', 550, 90),
                                                                                  ('チャイニーズバーガー', '/images/13.jpg', 600, 80),
                                                                                  ('北欧バーガー', '/images/14.jpg', 650, 70),
                                                                                  ('イタリアンバーガー', '/images/15.jpg', 700, 60),
                                                                                  ('イングランドバーガー', '/images/16.jpg', 750, 50),
                                                                                  ('キッズバーガー', '/images/17.jpg', 800, 40),
                                                                                  ('コーラ', '/images/18.jpg', 850, 30),
                                                                                  ('Heineken', '/images/19.jpg', 900, 20),
                                                                                  ('ジンジャエール', '/images/20.jpg', 950, 10),
                                                                                  ('マルゲリータ', '/images/21.jpg', 900, 20),
                                                                                  ('ペパロニピザ', '/images/22.jpg', 900, 20),
                                                                                  ('メキシカンピザ', '/images/23.jpg', 900, 20),
                                                                                  ('ピザトースト', '/images/24.jpg', 900, 20);





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
                                                                           (5, 5, 1); -- 伊藤桃子がスパイシーチキンバーガーを1個予約

-- user_tableにデータを挿入
INSERT INTO user_table (authority_kind, user_name, user_password, user_tel) VALUES
                                                                                         ('1', 'yamataro', 'taro-y111', '080-1234-5678'),
                                                                                         ('1', 'suzuki1', 'ichiro51', '080-8765-4321'),
                                                                                         ('1', 'komomo110', '110momoko', '060-9988-7766');

