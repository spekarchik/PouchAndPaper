## 📦 Version 3.0.0

### ✨ New
- **Feathers Pack** can now be placed as a block and behaves like a feather container.
- **Leaves Pack** can now be placed as a block and behaves like a leaves container.

### 📝 Improvements
- Sounds for other blocks have been reviewed.


## 📦 Version 2.3.1

### 📝 Improvements
- Added sounds for refilling and unloading pouches and sacks.


## 📦 Version 2.2.1

### 📝 Improvements
- **Burnt Paper Block** no longer breaks from random mob steps.
  Now it breaks only if:
  - a player steps on it
  - or any entity falls on it from ≥1.5 blocks height.


## 📦 Version 2.2.0

### 📝 Improvements
- 🪶 Doubled the storage capacity of placed *pouches* and *sacks* **(now holds a full stack of pouches or sacks)**
- ⏩ Up to **16 pouches/sacks** or **64 seeds/crops** can now be added per use (or as many as possible)
- ✋ Right-clicking with an empty hand or incompatible item now withdraws **16 seeds/crops** instead of 4
- 🧍 While sneaking, *pouches/sacks* and *seeds/crops* are added **one at a time** (for precise filling)
- 🧠 Tooltip updated to reflect the new capacity and usage logic
- 🎨 Localizations updated: Belarusian and Russian

### 🛠️ Other Changes
- 🧪 In **Creative Mode**, *pouches* and *sacks* are no longer consumed when used on placed blocks


## 📦 Version 2.1.7

### 📝 Improvements
- Grouped the two Leather recipes using the `group` property


## 📦 Version 2.1.6

### 📝 Improvements
- Grouped the two Paper and two Paper Stack recipes using the `group` property to reduce clutter in recipe listings and advancements


## 📦 Version 2.1.5

### 📝 Improvements
- Changed pouch and crop sack breaking behavior:  
  — When broken while sneaking (shift), the block now drops full pouches/sacks instead of individual seeds/crops (one pouch per 4 units).  
  — Any leftover seeds/crops less than 4 units drop as individual items.  
  — Breaking without sneaking continues to drop all seeds/crops as individual items.  
- This change improves inventory management and consistency with stacking logic.


## 📦 Version 2.1.4

### 🛠️ Changed
- Increased capacity of pouches and sacks from 60 to 124 items.
- Pouches and sacks now drop 4 seeds/crops instead of the container itself.
- Updated tooltips to reflect the new capacity and drop behavior.


## 📦 Version 2.1.3

### 📝 Improvements
- Pouches now support **visual fill levels**, just like sacks.
- Fill level thresholds have been updated for both sacks and pouches:
  - **Empty** — less than **4** seeds or crops.
  - **Full** — more than **56** seeds or crops.


## 📦 Version 2.1.2

### 📝 Improvements
- Updated texture for Leather Pack.


## 📦 Version 2.1.1

### 📝 Improvements
- **Adjusted sack hitboxes** for more accurate collisions and visuals  
- **Sacks no longer transform Farmland into Dirt** when placed on top

### 🐞 Fixed
- **Blocked placement of sacks and pouches** on non-solid surfaces


## 📦 Version 2.1.0

### ✨ New
- Added three new **crop sacks**: **Sack of Potato**, **Sack of Carrot**, and **Sack of Beetroot**.
- These sacks behave similarly to seed pouches — they can be placed as blocks and used to store respective crops.
- Each crop sack shows different fill levels depending on how much crop is stored inside.
- Added full tooltip descriptions for all three new sacks.
- Added crafting recipes and advancements for each new crop sack.


## 📦 Version 2.0.1

### 🐞 Fixed
- Fixed visual artifacts on seed pouch models caused by transparent texture edges.


## 📦 Version 2.0.0

### ✨ New
- Seed pouches are now blocks: they can be placed in the world and used as seed storage containers.
- Added detailed descriptions to pouch tooltips.

### 📝 Improvements
- Updated texture for Feather Pack.


## 📦 Version 1.2.0

### ✨ New
- Added full support for Belarusian localization

### 📝 Improvements
- Improved tooltip formatting for `Paper Block` and `Burnt Paper Block`
- Improved text for Russian localization


## 📦 Version 1.1.0
- Initial publishing
