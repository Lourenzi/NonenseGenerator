// Wait for DOM to be fully loaded including Thymeleaf processing
document.addEventListener('DOMContentLoaded', function() {
    // Get title from Thymeleaf or use default
    const finalTitle = /*[[#{app.title}]]*/ 'NonSenseGenerator';
    const scrambled = "erenaetNeGsnonrSo";
    const titleEl = document.getElementById("title");
    
    // Only run scramble animation if title element exists
    if (titleEl) {
        scrambled.split("").forEach((char) => {
            const span = document.createElement("span");
            span.textContent = char;
            span.classList.add("scramble");
            titleEl.appendChild(span);
        });
        
        setTimeout(unscramble, 250);
    }

    // Initialize form with CSRF token for AJAX requests
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

    // Attach event listeners
    document.getElementById('copyBtn')?.addEventListener('click', onCopyClick);
    document.querySelector('.generate-button')?.addEventListener('click', onGenerateClick);
});

function unscramble() {
    const finalTitle = /*[[#{app.title}]]*/ 'NonSenseGenerator';
    const spans = document.querySelectorAll(".scramble");
    
    finalTitle.split("").forEach((targetChar, idx) => {
        const span = spans[idx];
        if (span) {
            setTimeout(() => {
                span.textContent = targetChar;
            }, idx * 250);
        }
    });
}

function onGenerateClick() {
    const inputText = document.getElementById('inputText').value;
    const sentenceCount = document.getElementById('sentenceCount').value;
    const includeTree = document.getElementById('syntacticTree').checked;
    const outputBox = document.getElementById('outputBox');
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

    // Reset animation
    outputBox.classList.remove('glow-animation');
    void outputBox.offsetWidth;
    
    // Make AJAX call to Spring Boot endpoint
    fetch('/generate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            [csrfHeader]: csrfToken
        },
        body: `inputText=${encodeURIComponent(inputText)}&sentenceCount=${sentenceCount}&syntacticTree=${includeTree}`
    })
    .then(response => response.text())
    .then(html => {
        // Create temporary DOM to parse response
        const parser = new DOMParser();
        const doc = parser.parseFromString(html, 'text/html');
        
        // Update output text
        const newOutput = doc.getElementById('outputText').textContent;
        document.getElementById('outputText').textContent = newOutput;
        
        // Update syntactic tree checkbox state
        const newCheckboxState = doc.getElementById('syntacticTree').checked;
        document.getElementById('syntacticTree').checked = newCheckboxState;
        
        // Trigger animation
        outputBox.classList.add('glow-animation');
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('outputText').textContent = 'Error generating text';
        outputBox.classList.add('glow-animation');
    });
}

function onCopyClick() {
    const output = document.getElementById('outputText').textContent;
    const button = document.getElementById('copyBtn');
    const originalText = /*[[#{copy.button.text}]]*/ 'Copy';
    const copiedText = '\u2713 Copied';
    
    if (!output.trim()) {
        return;
    }
    
    button.textContent = copiedText;
    button.style.width = button.offsetWidth + 'px';
    
    navigator.clipboard.writeText(output).then(() => {
        setTimeout(() => {
            button.textContent = originalText;
        }, 2000);
    }).catch(err => {
        console.error('Failed to copy text: ', err);
        button.textContent = 'Error';
        setTimeout(() => {
            button.textContent = originalText;
        }, 2000);
    });
}