insert into patient(name,surname) values
  ('Jan','Kowalski'),
  ('Adam','Fryc'),
  ('Albert','Grudziński'),
  ('Kacper','Czyż'),
  ('Kacper','Domagała');

insert into test_type(name,price,description) values
  ('Serum Iron','2.5','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Malesuada fames ac turpis egestas maecenas.'),
  ('TIBC','15.9','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Malesuada fames ac turpis egestas maecenas.'),
  ('Transferrin','12.0','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Malesuada fames ac turpis egestas maecenas.'),
  ('Hepcidin','254.8','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Malesuada fames ac turpis egestas maecenas.');

insert into visit(date,patient_id,test_type_id) values
  ('02.12.2018',5,4),
  ('15.12.2018',1,2),
  ('28.12.2018',2,3);