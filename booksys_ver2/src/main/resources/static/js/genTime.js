function genHour() {
  let list = document.getElementsByClassName('hour');
  list[0].appendChild(document.createElement('option'));
  for (let j = 0; j < 24; j++) {
    let h = document.createElement('option');
    h.value = j;
    h.innerText = j;
    list[0].appendChild(h);
  }
}

function genMinute(min) {
  let list = document.getElementsByClassName('minute');
  list[0].appendChild(document.createElement('option'));
  for (let i = 0; i < list.length; i++) {
    let m = document.createElement('option');
    m.value = min;
    m.innerText = min;
    list[0].appendChild(m);
  }
}
genHour();
genMinute(00);
genMinute(30);
