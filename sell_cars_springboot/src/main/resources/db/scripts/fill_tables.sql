INSERT INTO car_body
(body_type)
VALUES
('Hatchback'),
('Sedan'),
('Crossover'),
('Coupe'),
('MPV'),
('Fastback'),
('SUV')
ON CONFLICT DO NOTHING;

INSERT INTO transmission
(transmission_type)
VALUES
('Automatic'),
('Automated-Manual'),
('Manual'),
('Dual-Clutch'),
('Direct Shift Gearbox'),
('Tiptronic')
ON CONFLICT DO NOTHING;

INSERT INTO car_brand
(car_brand)
VALUES
('Audi'),
('BMW'),
('Jeep'),
('Porsche'),
('Ford'),
('Ferrari')
ON CONFLICT DO NOTHING;

INSERT INTO engine
(engine_type)
VALUES
('VEE'),
('INLINE'),
('Boxer')
ON CONFLICT DO NOTHING;

