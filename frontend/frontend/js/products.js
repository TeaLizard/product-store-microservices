// const PRODUCT_API = "http://localhost:8080/products";
// const CART_API = "http://localhost:8080/cart";
// const INVENTORY_API = "http://localhost:8080/inventory";
//
// async function loadProducts() {
//     const res = await fetch(PRODUCT_API);
//     const products = await res.json();
//
//     const grid = document.getElementById("product-grid");
//     grid.innerHTML = "";
//
//     products.forEach(p => {
//         grid.innerHTML += `
//             <div class="bg-gray-900 p-6 rounded-2xl shadow hover:shadow-2xl transition">
//                 <img src="${p.image}" class="w-full h-64 object-cover rounded-xl mb-4">
//                 <h2 class="text-3xl font-bold">${p.name}</h2>
//                 <p class="text-gray-300 mb-2">${p.description}</p>
//                 <p class="text-xl font-bold mb-4">$${p.price}</p>
//
//                 <button
//                     class="px-6 py-3 bg-white text-black font-bold rounded-xl hover:scale-105 transition"
//                     onclick="addToCart(${p.id})">
//                     Add to Cart
//                 </button>
//             </div>
//         `;
//     });
// }
//
// async function addToCart(productId) {
//     const url = `${CART_API}/add-to-cart/${productId}`;
//
//     const res = await fetch(url, { method: "PUT" });
//
//     alert("Added to cart!");
// }
//
// document.addEventListener("DOMContentLoaded", loadProducts);

const PRODUCT_API = "http://localhost:8080/products";
const CART_API = "http://localhost:8080/cart";
const INVENTORY_API = "http://localhost:8080/inventory";

async function loadProducts() {
    const [productsRes, cartRes] = await Promise.all([
        fetch(PRODUCT_API),
        fetch(CART_API)
    ]);

    const products = await productsRes.json();
    const cart = await cartRes.json();

    // Build a map of productId -> quantity in cart
    const cartMap = {};
    cart.products.forEach(p => {
        cartMap[p.id] = (cartMap[p.id] || 0) + 1;
    });

    const grid = document.getElementById("product-grid");
    grid.innerHTML = "";

    for (const p of products) {
        // Fetch inventory for this product
        const invRes = await fetch(`${INVENTORY_API}/${p.id}`);
        const inventory = await invRes.json();

        const inCart = cartMap[p.id] || 0;
        const available = inventory.quantity - inCart;

        grid.innerHTML += `
            <div class="bg-gray-900 p-6 rounded-2xl shadow hover:shadow-2xl transition">
                <img src="${p.image}" class="w-full h-64 object-cover rounded-xl mb-4">
                <h2 class="text-3xl font-bold">${p.name}</h2>
                <p class="text-gray-300 mb-2">${p.description}</p>
                <p class="text-xl font-bold mb-4">$${p.price}</p>

                <button
                    class="px-6 py-3 ${available <= 0 ? "bg-gray-500 cursor-not-allowed" : "bg-white hover:scale-105"} text-black font-bold rounded-xl transition"
                    onclick="${available > 0 ? `addToCart(${p.id})` : ""}"
                    ${available <= 0 ? "disabled" : ""}>
                    ${available > 0 ? "Add to Cart" : "Out of Stock"}
                </button>
            </div>
        `;
    }
}

async function addToCart(productId) {
    const url = `${CART_API}/add-to-cart/${productId}`;
    await fetch(url, { method: "PUT" });
    alert("Added to cart!");
    // reload products to refresh availability
    loadProducts();
}

document.addEventListener("DOMContentLoaded", loadProducts);