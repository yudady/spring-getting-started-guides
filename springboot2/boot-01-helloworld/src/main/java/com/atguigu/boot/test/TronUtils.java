// package com.atguigu.boot.test;
//
// import com.google.protobuf.Any;
// import com.google.protobuf.ByteString;
// import com.google.protobuf.InvalidProtocolBufferException;
// import org.apache.commons.lang3.ArrayUtils;
// import org.apache.commons.lang3.StringUtils;
// import org.bouncycastle.util.encoders.Hex;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.tron.api.GrpcAPI;
// import org.tron.common.crypto.ECKey;
// import org.tron.common.utils.Base58;
// import org.tron.common.utils.ByteArray;
// import org.tron.protos.Protocol;
// import org.tron.protos.contract.BalanceContract;
// import org.tron.protos.contract.SmartContractOuterClass;
// import org.tron.walletserver.WalletApi;
//
// import java.math.BigDecimal;
// import java.math.BigInteger;
// import java.security.MessageDigest;
// import java.security.NoSuchAlgorithmException;
// import java.security.SecureRandom;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;
//
// /**
//  * tron-utils 节点交互
//  *
//  * @Autor YZH 2021/1/20
//  * @Date 2021-01-20 18:46:18
//  */
// public class TronUtils {
//
//     private static Logger logger = LoggerFactory.getLogger(TronUtils.class);
//     static int ADDRESS_SIZE = 21;
//     private static byte addressPreFixByte = (byte) 0x41; // 41 + address (byte) 0xa0; //a0 + address
//     private static SecureRandom random = new SecureRandom();
//
//     private void rpcTransactionInfo(List<String> addressList, Long num) {
//         try {
//             Optional<GrpcAPI.TransactionInfoList> optional = WalletApi.getTransactionInfoByBlockNum(num);
//             if (!optional.isPresent()) {
//                 return;
//             }
//
//             List<Protocol.TransactionInfo> transactionInfoList = optional.get().getTransactionInfoList();
//             for (Protocol.TransactionInfo transactionInfo : transactionInfoList) {
//                 String txId = ByteArray.toHexString(transactionInfo.getId().toByteArray());
//                 //判断 数据库 txId 有 就不用往下继续了
//
//                 Optional<Protocol.Transaction> transaction = WalletApi.getTransactionById(txId);
//                 if (!transaction.isPresent()) {
//                     continue;
//                 }
//
//                 List<Protocol.Transaction.Result> retList = transaction.get().getRetList();
//                 Protocol.Transaction.Result.contractResult contractRet = retList.get(0).getContractRet();
//                 if (!Protocol.Transaction.Result.contractResult.SUCCESS.name().equals(contractRet.name())) {
//                     continue;
//                 }
//
//                 Protocol.Transaction.Contract.ContractType type = transaction.get().getRawData().getContract(0).getType();
//                 Any contractParameter = transaction.get().getRawData().getContract(0).getParameter();
//
//                 if (Protocol.Transaction.Contract.ContractType.TriggerSmartContract.name().equals(type.name())) {
//                     // trc20 充值
//
//                     SmartContractOuterClass.TriggerSmartContract deployContract = contractParameter
//                             .unpack(SmartContractOuterClass.TriggerSmartContract.class);
//
//                     String owner_address = WalletApi.encode58Check(ByteArray.fromHexString(ByteArray.toHexString(deployContract.getOwnerAddress().toByteArray())));
//                     String contract_address = WalletApi.encode58Check(ByteArray.fromHexString(ByteArray.toHexString(deployContract.getContractAddress().toByteArray())));
//
//                     String dataStr = ByteArray.toHexString(deployContract.getData().toByteArray()).substring(8);
//                     List<String> strList = TransformUtil.getStrList(dataStr, 64);
//                     if (strList.size() != 2) {
//                         continue;
//                     }
//
//                     String to_address = TransformUtil.delZeroForNum(strList.get(0));
//                     if (!to_address.startsWith("41")) {
//                         to_address = "41" + to_address;
//                     }
//
//                     to_address = WalletApi.encode58Check(ByteArray.fromHexString(to_address));
//
//                     String amountStr = TransformUtil.delZeroForNum(strList.get(1));
//                     if (amountStr.length() > 0) {
//                         amountStr = new BigInteger(amountStr, 16).toString(10);
//                     }
//
//                     BigDecimal amount = BigDecimal.ZERO;
//                     //相匹配的合约地址
//                     if (!contractMap.containsKey(contract_address)) {
//                         continue;
//                     }
//                     //合约币种
//                     String symbol = contractMap.get(contract_address);
//                     if (StringUtils.isNotEmpty(amountStr)) {
//                         amount = new BigDecimal(amountStr).divide(new BigDecimal(1 + TransformUtil.getSeqNumByLong(0L, weiMap.get(symbol))));
//                     }
//                     for (String address : addressList) {
//                         if (address.equals(to_address)) {
//                             System.out.println("===to_address:" + to_address + "===amount:" + amount);
//                         }
//                     }
//
//                 } else if (Protocol.Transaction.Contract.ContractType.TransferContract.name().equals(type.name())) {
//                     // trx 充值
//                     BalanceContract.TransferContract deployContract = contractParameter
//                             .unpack(BalanceContract.TransferContract.class);
//                     String owner_address = WalletApi.encode58Check(ByteArray.fromHexString(ByteArray.toHexString(deployContract.getOwnerAddress().toByteArray())));
//                     String to_address = WalletApi.encode58Check(ByteArray.fromHexString(ByteArray.toHexString(deployContract.getToAddress().toByteArray())));
//                     BigDecimal amount = new BigDecimal(deployContract.getAmount());
//                     amount = amount.divide(new BigDecimal(1 + TransformUtil.getSeqNumByLong(0L, weiMap.get("TRX"))));
//
//                 }
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
//
//     /**
//      * {
//      * "address": "TJfTWgzey3d3QHbrautjRmANNULfk91VFj",
//      * "hexAddress": "415f5dc2006f98936ca054ea49b4d38d292c9429c8"
//      * }
//      */
//     public static void main(String[] args) {
//         String wallet = "TJfTWgzey3d3QHbrautjRmANNULfk91VFj";
//         System.out.println("wallet = " + wallet);
//         String toHexAddress = toHexAddress(wallet);
//         System.out.println("toHexAddress = " + toHexAddress);
//
//         String viewAddress = toViewAddress(toHexAddress);
//         System.out.println("viewAddress = " + viewAddress);
//
//         ByteArray.toHexString(WalletApi.decodeFromBase58Check(""));
//     }
//
//     /**
//      * 转换成hex地址
//      *
//      * @param address
//      * @return
//      */
//     public static String toHexAddress(String address) {
//         if (StringUtils.isEmpty(address)) {
//             throw new IllegalArgumentException("传入的地址不可为空");
//         }
//         if (!address.startsWith("T")) {
//             throw new IllegalArgumentException("传入地址不合法:" + address);
//         }
//         return Hex.toHexString(decodeFromBase58Check(address));
//     }
//
//     /**
//      * 离线创建地址
//      *
//      * @return
//      */
//     public static Map<String, String> createAddress() {
//         ECKey eCkey = new ECKey(random);
//         String privateKey = ByteArray.toHexString(eCkey.getPrivKeyBytes());
//         byte[] addressBytes = eCkey.getAddress();
//         String hexAddress = ByteArray.toHexString(addressBytes);
//         Map<String, String> addressInfo = new HashMap<>(3);
//         addressInfo.put("address", toViewAddress(hexAddress));
//         addressInfo.put("hexAddress", hexAddress);
//         addressInfo.put("privateKey", privateKey);
//         return addressInfo;
//     }
//
//     /**
//      * 根据私钥获取地址
//      *
//      * @param privateKey
//      * @return
//      */
//     public static String getAddressByPrivateKey(String privateKey) {
//         byte[] privateBytes = Hex.decode(privateKey);
//         ECKey ecKey = ECKey.fromPrivate(privateBytes);
//         byte[] from = ecKey.getAddress();
//         return toViewAddress(Hex.toHexString(from));
//     }
//     //
//     // /**
//     //  * 广播交易信息 返回交易id
//     //  * @param tronUrl
//     //  * @param transaction
//     //  * @return
//     //  */
//     // public static String signAndBroadcast(String tronUrl,String privateKey,JSONObject transaction)throws Throwable{
//     // 	if(tronUrl.endsWith("/")){
//     // 		tronUrl= tronUrl.substring(0,tronUrl.length() - 1);
//     // 	}
//     // 	Protocol.Transaction tx = packTransaction(transaction.toJSONString());
//     // 	byte[] bytes = signTransactionByte(tx.toByteArray(), ByteArray.fromHexString(privateKey));
//     // 	String signTransation = Hex.toHexString(bytes);
//     // 	JSONObject jsonObjectGB = new JSONObject();
//     // 	jsonObjectGB.put("transaction", signTransation);
//     // 	String url = tronUrl + "/wallet/broadcasthex";
//     // 	String transationCompelet1 = HttpClientUtils.postJson(url, jsonObjectGB.toString());
//     // 	JSONObject transationCompelet = JSONObject.parseObject(transationCompelet1);
//     // 	System.out.println("signAndBroadcast transationCompelet:" + transationCompelet.toJSONString());
//     // 	if (transationCompelet.getBoolean("result")) {
//     // 		return transationCompelet.getString("txid");
//     // 	} else {
//     // 		logger.error(String.format("签名交易失败:%s",transationCompelet1));
//     // 		return null;
//     // 	}
//     // }
//
//     /**
//      * 签名交易
//      *
//      * @param transaction
//      * @param privateKey
//      * @return
//      * @throws InvalidProtocolBufferException
//      * @throws NoSuchAlgorithmException
//      */
//     public static byte[] signTransactionByte(byte[] transaction, byte[] privateKey) throws InvalidProtocolBufferException, NoSuchAlgorithmException {
//         ECKey ecKey = ECKey.fromPrivate(privateKey);
//         Protocol.Transaction transaction1 = Protocol.Transaction.parseFrom(transaction);
//         byte[] rawdata = transaction1.getRawData().toByteArray();
//         MessageDigest digest = MessageDigest.getInstance("SHA-256");
//         digest.update(rawdata, 0, rawdata.length);
//         byte[] hash = digest.digest();
//         byte[] sign = ecKey.sign(hash).toByteArray();
//         return transaction1.toBuilder().addSignature(ByteString.copyFrom(sign)).build().toByteArray();
//     }
//
//     /**
//      * 转换成T开头的地址
//      *
//      * @param hexAddress
//      * @return
//      */
//     public static String toViewAddress(String hexAddress) {
//         return encode58Check(ByteArray.fromHexString(hexAddress));
//     }
//
//     public static String encode58Check(byte[] input) {
//         try {
//             byte[] hash0 = hash(true, input);
//             byte[] hash1 = hash(true, hash0);
//             byte[] inputCheck = new byte[input.length + 4];
//             System.arraycopy(input, 0, inputCheck, 0, input.length);
//             System.arraycopy(hash1, 0, inputCheck, input.length, 4);
//             return Base58.encode(inputCheck);
//         } catch (Throwable t) {
//             logger.error(String.format("data error:%s", Hex.toHexString(input)), t);
//         }
//         return null;
//     }
//
//     private static byte[] decode58Check(String input) throws Exception {
//         byte[] decodeCheck = Base58.decode(input);
//         if (decodeCheck.length <= 4) {
//             return null;
//         }
//         byte[] decodeData = new byte[decodeCheck.length - 4];
//         System.arraycopy(decodeCheck, 0, decodeData, 0, decodeData.length);
//         byte[] hash0 = hash(true, decodeData);
//         byte[] hash1 = hash(true, hash0);
//         if (hash1[0] == decodeCheck[decodeData.length] && hash1[1] == decodeCheck[decodeData.length + 1]
//                 && hash1[2] == decodeCheck[decodeData.length + 2] && hash1[3] == decodeCheck[decodeData.length + 3]) {
//             return decodeData;
//         }
//         return null;
//     }
//
//     /**
//      * Calculates the SHA-256 hash of the given bytes.
//      *
//      * @param input the bytes to hash
//      * @return the hash (in big-endian order)
//      */
//     public static byte[] hash(boolean isSha256, byte[] input) throws NoSuchAlgorithmException {
//         return hash(isSha256, input, 0, input.length);
//     }
//
//     /**
//      * Calculates the SHA-256 hash of the given byte range.
//      *
//      * @param input  the array containing the bytes to hash
//      * @param offset the offset within the array of the bytes to hash
//      * @param length the number of bytes to hash
//      * @return the hash (in big-endian order)
//      */
//     public static byte[] hash(boolean isSha256, byte[] input, int offset, int length) throws NoSuchAlgorithmException {
//         if (isSha256) {
//             MessageDigest digest = MessageDigest.getInstance("SHA-256");
//             digest.update(input, offset, length);
//             return digest.digest();
//         } else {
//             // SM3Digest digest = new SM3Digest();
//             // digest.update(input, offset, length);
//             // byte[] eHash = new byte[digest.getDigestSize()];
//             // digest.doFinal(eHash, 0);
//             // return eHash;
//             return null;
//         }
//     }
//
//
//     public static byte[] decodeFromBase58Check(String addressBase58) {
//         try {
//             byte[] address = decode58Check(addressBase58);
//             if (!addressValid(address)) {
//                 return null;
//             }
//             return address;
//         } catch (Throwable t) {
//             logger.error(String.format("decodeFromBase58Check-error:" + addressBase58), t);
//         }
//         return null;
//     }
//
//     private static boolean addressValid(byte[] address) {
//         if (ArrayUtils.isEmpty(address)) {
//             return false;
//         }
//         if (address.length != ADDRESS_SIZE) {
//             return false;
//         }
//         byte preFixbyte = address[0];
//         return preFixbyte == addressPreFixByte;
//         // Other rule;
//     }
//
//
// }
