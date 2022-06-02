//
//  jwt.swift
//  jwt
//
//  Created by Jemo Mgebrishvili on 01.06.22.
//
import Foundation

@objc public class JwtTokenParser : NSObject{

    @objc
    func decodeToken(jwtToken: String) -> [String: Any]? {
        do {
            return try decode(jwtToken: jwtToken)
        } catch {
            return nil
        }
    }

    enum DecodeErrors: Error {
        case badToken
        case other
    }


    @objc
    func decode(jwtToken jwt: String) throws -> [String: Any] {
        let segments = jwt.components(separatedBy: ".")
        return try decodeJWTPart(segments[1])
    }
    
    @objc
    func decodeJWTPart(_ value: String) throws -> [String: Any] {
        let bodyData = try base64Decode(value)
        let json = try JSONSerialization.jsonObject(with: bodyData, options: [])
        guard let payload = json as? [String: Any] else {
            throw DecodeErrors.other
        }
        return payload
    }
    
    @objc
    func base64Decode(_ base64: String) throws -> Data {
        let padded = base64.padding(toLength: ((base64.count + 3) / 4) * 4, withPad: "=", startingAt: 0)
        guard let decoded = Data(base64Encoded: padded) else {
            throw DecodeErrors.badToken
        }
        return decoded
    }
}
