const PRODUCT_API = "http://localhost:8080/products";
const CART_API = "http://localhost:8080/cart";

async function loadProducts() {
    const res = await fetch(PRODUCT_API);
    const products = await res.json();

    const grid = document.getElementById("product-grid");
    grid.innerHTML = "";

    products.forEach(p => {
        grid.innerHTML += `
            <div class="bg-gray-900 p-6 rounded-2xl shadow hover:shadow-2xl transition">
                <img src="${p.image}" class="w-full h-64 object-cover rounded-xl mb-4">
                <h2 class="text-3xl font-bold">${p.name}</h2>
                <p class="text-gray-300 mb-2">${p.description}</p>
                <p class="text-xl font-bold mb-4">$${p.price}</p>

                <button
                    class="px-6 py-3 bg-white text-black font-bold rounded-xl hover:scale-105 transition"
                    onclick="addToCart(${p.id})">
                    Add to Cart
                </button>
            </div>
        `;
    });
}

async function addToCart(productId) {
    const url = `${CART_API}/add-to-cart/${productId}`;

    const res = await fetch(url, { method: "PUT" });

    alert("Added to cart!");
}

document.addEventListener("DOMContentLoaded", loadProducts);