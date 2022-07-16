-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Jul 16, 2022 at 12:15 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `joecar`
--

-- --------------------------------------------------------

--
-- Table structure for table `brands`
--

CREATE TABLE `brands` (
  `brandId` int(11) NOT NULL,
  `brandName` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `brands`
--

INSERT INTO `brands` (`brandId`, `brandName`) VALUES
(0, 'Toyota'),
(1, 'Suzuki'),
(2, 'Audi');

-- --------------------------------------------------------

--
-- Table structure for table `models`
--

CREATE TABLE `models` (
  `modelId` int(11) NOT NULL,
  `parentBrandId` int(11) NOT NULL,
  `modelName` text NOT NULL,
  `modelYear` int(11) NOT NULL,
  `hasSunroof` int(11) NOT NULL,
  `doorCount` int(11) NOT NULL,
  `seatCount` int(11) NOT NULL,
  `fuelCapacity` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `models`
--

INSERT INTO `models` (`modelId`, `parentBrandId`, `modelName`, `modelYear`, `hasSunroof`, `doorCount`, `seatCount`, `fuelCapacity`) VALUES
(0, 0, 'Alphard', 1234, 1, 10, 20, 4235),
(1, 1, 'Suzuki X', 1200, 1, 20, 30, 325);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `transactionId` int(11) NOT NULL,
  `parentVehicleId` int(11) NOT NULL,
  `paidAmount` int(11) NOT NULL,
  `dateOfTransactionDate` int(11) NOT NULL,
  `dateOfTransactionMonth` int(11) NOT NULL,
  `dateOfTransactionYear` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`transactionId`, `parentVehicleId`, `paidAmount`, `dateOfTransactionDate`, `dateOfTransactionMonth`, `dateOfTransactionYear`) VALUES
(0, 0, 20000000, 30, 5, 2022);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userId` int(11) NOT NULL,
  `userName` text NOT NULL,
  `password` text NOT NULL,
  `salt` text NOT NULL,
  `userLevel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userId`, `userName`, `password`, `salt`, `userLevel`) VALUES
(0, 'admin', 'f9e61af08b17a4985fffed2977409ba58697dd829cbe8c7327809e7311a10527', '05cc9363e1fd7873aad8704775b3d82c9bb33a41a86cc20db177b296b85a168b', 0),
(1, 'sales', 'c38455bdd1e2cb6032d8c7dd5dc257dec9f2bb8c3b8e3516a89d4def988faf3b', '57a2a2f54f4728350d7a8fa3fed6a6932928ddd85394fd86b4ba3025356cb88d', 1),
(2, 'product', '3bec7b295776207de804fa67574ea33e239fe1f867e925db6c3648770400b3d5', '0652ed0189af8b01526a81773ab03b587d344da339dda059055493c54497ff19', 2),
(3, 'adaf', '08210bb298d92702f037056309747280be05b92f30dbe8ddbe3ac60c017c974f', '15ca3fffcd36a999529d3740778181c83fb4dac14ef4fe98a13a293953c07e36', 0),
(4, 'davv', 'e57d3efa048e69c1a6406b3bebe01a55ef375b044d62e04d4ce86a3ac096f1f5', '5de56e4474e34779a3bdb2c8a50fccb1e57b9cdfcf1743e44f19ff4bb608c388', 1),
(5, 'user', 'e80653af8b4846e7635ad1abdc7b9410198ac66a9985ed8dc239a28e60fc5b91', 'c0a662ca04822fc1a856d016fa8a321512d5fa1d5572700687cdb044c778b8fc', 3),
(6, 'vip', 'e01d245f609d74eefc86e3b4daa2fcd53b45daa028e3bc3f60949b31bb9531b4', 'c961b7335912eec17f5b4807abf933f46f4a51f80f7e1a0abd9b1f91ea88bee5', 4),
(7, 'thomas', 'a1679cda0abfe385ca6e1abc1aa5fc42fd16ae2c6af627f0f5aedea125525c08', 'a96b85d4f79d16312ac03c4c77775e3539631ecb60ed842c94ef8d70aa264f26', 3);

-- --------------------------------------------------------

--
-- Table structure for table `vehicles`
--

CREATE TABLE `vehicles` (
  `vehicleId` int(11) NOT NULL,
  `parentModelId` int(11) NOT NULL,
  `VIN` text NOT NULL,
  `licensePlate` text NOT NULL,
  `color` text NOT NULL,
  `mileage` double NOT NULL,
  `discount` decimal(10,0) NOT NULL,
  `price` int(11) NOT NULL,
  `buyerUserId` int(11) NOT NULL,
  `sellerUserId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicles`
--

INSERT INTO `vehicles` (`vehicleId`, `parentModelId`, `VIN`, `licensePlate`, `color`, `mileage`, `discount`, `price`, `buyerUserId`, `sellerUserId`) VALUES
(0, 0, '1', 'D 9999 X', 'red', 4, '6', 120500000, 6, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `brands`
--
ALTER TABLE `brands`
  ADD PRIMARY KEY (`brandId`);

--
-- Indexes for table `models`
--
ALTER TABLE `models`
  ADD PRIMARY KEY (`modelId`),
  ADD UNIQUE KEY `parentBrandId` (`parentBrandId`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`transactionId`);

--
-- Indexes for table `vehicles`
--
ALTER TABLE `vehicles`
  ADD PRIMARY KEY (`vehicleId`),
  ADD UNIQUE KEY `parentModelId` (`parentModelId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
