INSERT INTO rgon_m (
    rgon_nm,
    delete_flg,
    rgon_use_typ,
    disp_order
) VALUES
      ('Hokkaido',   '0', 1, '01'),
      ('Tohoku',     '0', 1, '02'),
      ('Kanto',      '0', 1, '03'),
      ('Chubu',      '0', 1, '04'),
      ('Kinki',      '0', 1, '05'),
      ('Chugoku',    '0', 0, '06'),
      ('Shikoku',    '0', 1, '07'),
      ('Kyushu',     '0', 0, '08'),
      ('Okinawa',    '0', 0, '09'),
      ('Nationwide', '0', 1, '10'),
      ('Abroad',     '0', 1, '11'),
      ('North',      '1', 1, '12'),
      ('South',      '1', 1, '13'),
      ('East',       '0', 1, '14'),
      ('West',       '0', 0, '15');


INSERT INTO prefecture_m (rgon_cd, prefecture_nm) VALUES
(1, 'Hokkaido'),
(2, 'Aomori'),
(2, 'Iwate'),
(3, 'Tokyo'),
(3, 'Kanagawa'),
(3, 'Chiba'),
(4, 'Nagano'),
(4, 'Niigata'),
(5, 'Osaka'),
(5, 'Kyoto'),
(6, 'Hiroshima'),
(7, 'Ehime'),
(8, 'Fukuoka'),
(8, 'Kumamoto'),
(9, 'Naha');

INSERT INTO contact_m (ref_nm, ref_kn, tel_no, prefecture_cd) VALUES
('Woof Woof Festival Office', 'Wanwan Festival Gym Kyoku', '03-1111-0001', 5),
('ONE ROOM', 'one room', '03-1111-0002', 5),
('One missing', 'One missing', '03-1111-0003', 4),
('One Heart Project', 'One Heart Kikaku', '03-1111-0004', 4),
('one piece restaurant', 'one piece restaurant', '03-1111-0005', 4),
('Blue Sky Cafe', 'blue sky cafe', '045-222-1001', 5),
('Green Garden', 'green garden', '045-222-1002', 6),
('Sunshine Mall', 'sunshine mall', '06-3333-1001', 9),
('Happy Pets', 'happy pets', '06-3333-1002', 9),
('Ocean View', 'ocean view', '092-444-2001', 13),
('Mountain Lodge', 'mountain lodge', '026-555-3001', 7),
('River Side', 'river side', '025-666-3002', 8),
('Urban Works', 'urban works', '03-7777-4001', 4),
('Creative Lab', 'creative lab', '03-7777-4002', 4),
('Future Tech', 'future tech', '03-7777-4003', 4);

INSERT INTO url_m (url, screen_cd, screen_nm)
VALUES (
'<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8"/>
  <title>Batch selection of contact information</title>
  <style>
    body { font-family: Arial, sans-serif; background: #f7f8fa; padding: 20px; }
    .container { background: #fff; padding: 20px; border-radius: 8px; }
    .field-row { display: flex; gap: 20px; margin-bottom: 15px; }
    input[type=text] { width: 100%; padding: 10px; }
    table { width: 100%; border-collapse: collapse; margin-top: 20px; }
    th, td { padding: 10px; border-bottom: 1px solid #ddd; }
    th { background: #f1f3f6; }
  </style>
</head>

<body>
<div class="container">

  <h2>Batch selection of contact information</h2>

  <!-- Search Conditions -->
  <h3>Search conditions</h3>

  <div class="field-row">
    <div>
      <label>Contact name</label>
      <input type="text" name="contactName"/>
    </div>
    <div>
      <label>Contact name kana</label>
      <input type="text" name="contactKana"/>
    </div>
  </div>

  <!-- Dynamic Region Checkbox List -->
  <label>Region</label>
  <div>
    <label th:each="region : ${regionList}">
      <input type="checkbox"
             name="region"
             th:value="${region.rgonCd}"/>
      <span th:text="${region.rgonNm}"></span>
    </label>
  </div>

  <div style="margin-top:20px">
    <button>Clear</button>
    <button>Search</button>
  </div>

  <!-- Result Table -->
  <h3>List of contacts</h3>

  <table>
    <thead>
      <tr>
        <th>Contact code</th>
        <th>Contact name</th>
        <th>Contact name kana</th>
        <th>Prefecture</th>
        <th>Region</th>
      </tr>
    </thead>
    <tbody>
      <tr th:each="c : ${contactList}">
        <td th:text="${c.refCd}"></td>
        <td th:text="${c.refNm}"></td>
        <td th:text="${c.refKn}"></td>
        <td th:text="${c.prefectureNm}"></td>
        <td th:text="${c.regionNm}"></td>
      </tr>
    </tbody>
  </table>

  <div style="margin-top:20px">
    <button>Cancel</button>
    <button>Decide</button>
  </div>

</div>
</body>
</html>',
1001,
'Contact Selection Screen'
);
