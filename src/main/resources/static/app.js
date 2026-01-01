const form = document.getElementById("form");
const input = document.getElementById("url");
const btn = document.getElementById("btn");

const result = document.getElementById("result");
const shortLinkEl = document.getElementById("shortLink");

const copyBtn = document.getElementById("copyBtn");
const copyStatus = document.getElementById("copyStatus");

const errorEl = document.getElementById("error");

function setError(msg) {
    errorEl.textContent = msg;
    errorEl.classList.toggle("hidden", !msg);
}

function setLoading(isLoading) {
    btn.disabled = isLoading;
    btn.textContent = isLoading ? "Сокращаю..." : "Сократить";
}

function setResult(shortUrl) {
    if (!shortUrl) {
        result.classList.add("hidden");
        return;
    }
    shortLinkEl.textContent = shortUrl;
    shortLinkEl.href = shortUrl;
    result.classList.remove("hidden");
}

form.addEventListener("submit", async (e) => {
    e.preventDefault();
    setError("");
    copyStatus.textContent = "";
    setResult("");

    const url = input.value.trim();
    if (!url) return;

    setLoading(true);

    try {
        const res = await fetch("/shorten", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ url })
        });


        if (!res.ok) {
            const text = await res.text().catch(() => "");
            throw new Error(text || `Ошибка API: ${res.status}`);
        }

        const data = await res.json();

        // В твоём проекте поле называется "shortcode",
        // но по факту там лежит уже "полная короткая ссылка" (base-url + "/" + code).
        const shortUrl = data.shortcode || data.shortUrl || data.url;

        if (!shortUrl) throw new Error("API вернул пустой ответ (нет short url)");

        setResult(shortUrl);
    } catch (err) {
        setError(err.message || "Что-то пошло не так");
    } finally {
        setLoading(false);
    }
});

copyBtn.addEventListener("click", async () => {
    const text = shortLinkEl.textContent?.trim();
    if (!text) return;

    try {
        await navigator.clipboard.writeText(text);
        copyStatus.textContent = "Скопировано ✅";
        setTimeout(() => (copyStatus.textContent = ""), 1500);
    } catch {
        copyStatus.textContent = "Не удалось скопировать";
    }
});
