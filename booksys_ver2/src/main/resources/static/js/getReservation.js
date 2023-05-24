function searchTableByName(userName) {
  //  user search booked table
  let table = document.getElementById('table-num');
  table.innerText = '1';
}

function genTableNum() {
  // get table count from sever and show user which table is available
  let table = document.getElementById('table-num');
  table.appendChild(document.createElement('option'));
  for (let i = 1; i < 17; i++) {
    let numOption = document.createElement('option');
    numOption.value = i;
    numOption.innerText = i;
    table.appendChild(numOption);
  }
}

function ccButton() {
  // cc: cancel and change
  let c1 = document.createElement('button');
  let c2 = document.createElement('button');
  c1.innerText = '변경';
  c2.innerText = '취소';
  c1.className = 'change-button';
  c2.className = 'cancel-button';
  // c1.onclick = "location.href = '../html/shiftTable.html'";
  c1.onclick = () => {
    location.href = '../html/shiftTable.html';
  };
  c2.onclick = () => {
    location.href = '../html/shiftTable.html';
  };
  return [c1, c2];
}

function searchReservationByName(userReservation) {
  // 예약자의 모든 예약을 server에서 받음
  let searchResult = document.getElementById('reservation-list');
  //마지막 td에 cc추가하기
  for (let list of userReservation) {
    let res = document.createElement('tr');
    res.innerHTML += `<td>` + `${list.a}` + `</td>`;
    res.innerHTML += `<td>` + `${list.b}` + `</td>`;
    res.innerHTML += `<td>` + `${list.c}` + `</td>`;
    res.innerHTML += `<td>` + `${list.d}` + `</td>`;
    res.innerHTML += `<td></td>`;

    for (let cc of ccButton()) {
      res.lastElementChild.appendChild(cc);
    }
    searchResult.appendChild(res);
  }
}

let userReservation = [
  {
    a: 'a',
    b: 'b',
    c: 'c',
    d: 'd',
  },
  {
    a: 'A',
    b: 'B',
    c: 'C',
    d: 'D',
  },
];

searchReservationByName(userReservation);
