INSERT INTO product (product_id, product_name, product_url, product_price, product_stock) VALUES
                                                                                              (1, 'たこ焼き', 'https://cdn.macaro-ni.jp/image/summary/108/108786/bPSqCOs6isa8vH8rGWVFZhiRcpQs4reeaT8ZlN8n.jpg', 300, 100),
                                                                                              (2, '焼きそば', 'https://www.esthetic.cc/column/column/img/01296.jpg', 400, 80),
                                                                                              (3, 'はしまき', 'https://yataiya-senjudo.com/wp-content/uploads/2021/01/hashimaki_33.jpg', 300, 60),
                                                                                              (4, 'りんご飴', 'https://www.daisho.co.jp/dw/wp-content/uploads/2023/07/HP%E3%83%AC%E3%82%B7%E3%83%94%E7%94%A8%E3%80%80%E3%81%82%E3%82%81%E3%80%80%E3%82%8A%E3%82%93%E3%81%94-scaled.jpg', 650, 70),
                                                                                              (5, 'スパイシーチキンバーガー', 'https://www.mcdonalds.co.jp/product_images/885/1180_Spicy%20Chicken%20Burger_2021.png?20240130170946', 550, 90);

INSERT INTO reservation (reservation_id, customer_name, reservation_time, customer_tel, reservation_email, status) VALUES
                                                                                                                       (1, '山田太郎', '2024-04-01 12:00:00', '080-1234-5678', 'yamada@example.com', '未確定'),
                                                                                                                       (2, '鈴木一郎', '2024-04-02 13:30:00', '080-8765-4321', 'suzuki@example.com', '確定'),
                                                                                                                       (3, '佐藤花子', '2024-04-03 15:00:00', '090-1122-3344', 'sato@example.com', '確定'),
                                                                                                                       (4, '田中次郎', '2024-04-04 17:45:00', '070-5566-7788', 'tanaka@example.com', 'キャンセル'),
                                                                                                                       (5, '伊藤桃子', '2024-04-05 19:30:00', '060-9988-7766', 'ito@example.com', '未確定');


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

