const CART_API = "http://localhost:8080/cart";

async function loadCart() {
    const res = await fetch(CART_API);
    const cart = await res.json();

    const container = document.getElementById("cart-items");
    container.innerHTML = "";

    if (!cart.products || cart.products.length === 0) {
        container.innerHTML = `<p class="text-2xl text-gray-300">Your cart is empty.</p>`;
        return;
    }

    cart.products.forEach(product => {
        container.innerHTML += `
            <div class="bg-gray-900 p-6 rounded-2xl flex justify-between items-center mb-4">
                <div>
                    <h2 class="text-3xl font-bold">${product.name}</h2>
                    <p class="text-xl text-gray-300">$${product.price}</p>
                </div>

                <button onclick="removeFromCart(${product.id})"
                        class="px-6 py-2 bg-red-500 rounded-xl text-white font-bold hover:bg-red-600">
                    Remove
                </button>
            </div>
        `;
    });
}

async function removeFromCart(productId) {
    await fetch(`${CART_API}/remove-from-cart/${productId}`, {
        method: "PUT"
    });

    loadCart();
}

async function emptyCart() {
    await fetch(`${CART_API}/empty-cart`, {
        method: "PUT"
    });

    loadCart();
}

async function checkout() {
    const location = "Waterloo";

    await fetch(`${CART_API}/checkout/${location}`, {
        method: "POST"
    });

    window.location.href = "checkout.html";
}

document.addEventListener("DOMContentLoaded", loadCart);