const finalTitle = "NonSenseGenerator";
const scrambled = "erenaetNeGsnonrSo";
const titleEl = document.getElementById("title");
scrambled.split("").forEach((char) => {
  const span = document.createElement("span");
  span.textContent = char;
  span.classList.add("scramble");
  titleEl.appendChild(span);
});
function unscramble() {
  const spans = document.querySelectorAll(".scramble");
  finalTitle.split("").forEach((targetChar, idx) => {
    const span = spans[idx];
    setTimeout(() => {
      span.textContent = targetChar;
    }, idx * 250);
  });
}
window.onload = () => {
  setTimeout(unscramble, 250);
}
function onGenerateClick() {
  const outputText = document.getElementById('outputText');
  const outputBox = document.getElementById('outputBox');
  outputText.innerText = '';
  outputBox.classList.remove('glow-animation');
  void outputBox.offsetWidth;
  outputBox.classList.add('glow-animation');
}
function onCopyClick() {
  const output = document.getElementById('outputText').innerText;
  const button = document.getElementById('copyBtn');
  const originalText = 'Copy';
  const copiedText = '\u2713 Copied';
  button.innerText = copiedText;
  button.style.width = button.offsetWidth + 'px';
  navigator.clipboard.writeText(output).then(() => {
    setTimeout(() => {
      button.innerText = originalText;
    }, 2000);
  });
}
